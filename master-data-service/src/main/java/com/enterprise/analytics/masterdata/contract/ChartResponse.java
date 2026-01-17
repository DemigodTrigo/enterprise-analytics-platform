package com.enterprise.analytics.masterdata.contract;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChartResponse {

    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    private String chartType;

    private List<ChartSeries> series;

    private String numberFormat;
}
