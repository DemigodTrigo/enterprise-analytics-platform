package com.enterprise.analytics.masterdata.service;

import com.enterprise.analytics.masterdata.contract.AnalyticsFilterCriteria;
import com.enterprise.analytics.masterdata.contract.ChartResponse;
import com.enterprise.analytics.masterdata.contract.ChartSeries;
import com.enterprise.analytics.masterdata.repository.BookingRepository;
import com.enterprise.analytics.masterdata.util.FilterHashUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final BookingRepository bookingRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ChartScaleService scaleService;

    private static final String REVENUE_PREFIX = "analytics:revenue-by-product:";
    private static final String STATUS_PREFIX  = "analytics:count-by-status:";

    public ChartResponse revenueByProduct(AnalyticsFilterCriteria filters) {

        String cacheKey = REVENUE_PREFIX + FilterHashUtil.hash(filters);

        try {
            String cached = redisTemplate.opsForValue().get(cacheKey);
            if (cached != null) {
                return objectMapper.readValue(cached, ChartResponse.class);
            }

            List<Object[]> rawData =
                    bookingRepository.revenueByProductFiltered(
                            filters.getFromDate(),
                            filters.getToDate(),
                            filters.getProduct(),
                            filters.getStatus(),
                            filters.getCountry()
                    );

            double maxValue = rawData.stream()
                    .mapToDouble(r -> ((Number) r[1]).doubleValue())
                    .max()
                    .orElse(0);

            String scale = scaleService.detectScale(maxValue);

            Map<String, Number> seriesData = new LinkedHashMap<>();
            for (Object[] row : rawData) {
                seriesData.put(
                        row[0].toString(),
                        scaleService.applyScale(((Number) row[1]).doubleValue(), scale)
                );
            }

            ChartResponse response = ChartResponse.builder()
                    .title("Revenue by Product")
                    .chartType("BAR")
                    .xAxisLabel("Product")
                    .yAxisLabel(scaleService.axisLabel("Revenue", scale))
                    .numberFormat(scale)
                    .series(List.of(
                            ChartSeries.builder()
                                    .name("Revenue")
                                    .color("#4CAF50")
                                    .data(seriesData)
                                    .build()
                    ))
                    .build();

            redisTemplate.opsForValue()
                    .set(cacheKey, objectMapper.writeValueAsString(response));

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Revenue analytics failed", e);
        }
    }


    public ChartResponse countByStatus() {

        List<Object[]> rawData = bookingRepository.countByStatus();

        Map<String, Number> seriesData = new HashMap<>();
        for (Object[] row : rawData) {
            seriesData.put(row[0].toString(), (Number) row[1]);
        }

        return ChartResponse.builder()
                .title("Bookings by Status")
                .chartType("PIE")
                .xAxisLabel("Status")
                .yAxisLabel("Count")
                .numberFormat("RAW")
                .series(List.of(
                        ChartSeries.builder()
                                .name("Bookings")
                                .color("#2196F3")
                                .data(seriesData)
                                .build()
                ))
                .build();
    }


}
