package com.enterprise.analytics.masterdata.service;

import com.enterprise.analytics.masterdata.domain.entity.CountryEntity;
import com.enterprise.analytics.masterdata.dto.CountryResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private static final String REDIS_KEY = "master:countries";

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CountryService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public List<CountryResponse> getAllCountries() {
        try{
            String json = redisTemplate.opsForValue().get(REDIS_KEY);
            if(json == null){
                throw new IllegalStateException("Master data not loaded in Redis");
            }
            List<CountryEntity> entities = objectMapper.readValue(json, new TypeReference<>() {});

            return entities.stream().map(c -> new CountryResponse(c.getCode(), c.getName())).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to read countries from Redis", e);
        }
    }

}
