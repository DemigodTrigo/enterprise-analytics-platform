package com.enterprise.analytics.masterdata.service;

import com.enterprise.analytics.masterdata.domain.entity.CityEntity;
import com.enterprise.analytics.masterdata.repository.CityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;


    public List<CityEntity> getCitiesByCountry(String countryCode) {
        String key = "master:cities:" + countryCode;

        try {
            String cached = redisTemplate.opsForValue().get(key);
            if (cached != null) {
                return List.of(objectMapper.readValue(cached, CityEntity[].class));
            }

            List<CityEntity> cities =
                    cityRepository.findByCountryCodeAndActiveTrue(countryCode);

            redisTemplate.opsForValue()
                    .set(key, objectMapper.writeValueAsString(cities));

            return cities;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load cities", e);
        }
    }

}
