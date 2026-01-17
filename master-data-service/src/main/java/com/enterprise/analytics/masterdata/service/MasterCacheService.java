package com.enterprise.analytics.masterdata.service;

import com.enterprise.analytics.masterdata.domain.entity.CountryEntity;
import com.enterprise.analytics.masterdata.domain.entity.ProductEntity;
import com.enterprise.analytics.masterdata.domain.entity.StatusEntity;
import com.enterprise.analytics.masterdata.repository.CountryRepository;
import com.enterprise.analytics.masterdata.repository.ProductRepository;
import com.enterprise.analytics.masterdata.repository.StatusRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterCacheService {

    private static final String COUNTRY_KEY = "master:countries";
    private static final String STATUS_KEY = "master:statuses";
    private static final String PRODUCT_KEY = "master:products";

    private final CountryRepository countryRepository;
    private final StatusRepository statusRepository;
    private final ProductRepository productRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MasterCacheService(CountryRepository countryRepository, RedisTemplate<String, String> redisTemplate, StatusRepository statusRepository, ProductRepository productRepository) {
        this.countryRepository = countryRepository;
        this.redisTemplate = redisTemplate;
        this.statusRepository = statusRepository;
        this.productRepository = productRepository;
    }

    public void refreshCountryCache() {
        try {
            List<CountryEntity> countries = countryRepository.findByActiveTrue();
            String json = objectMapper.writeValueAsString(countries);
            redisTemplate.opsForValue().set(COUNTRY_KEY, json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to refresh country cache", e);
        }
    }

    public void refreshStatusCache() {
        try {
            var statuses = statusRepository.findByActiveTrue();
            String json = objectMapper.writeValueAsString(statuses);
            redisTemplate.opsForValue().set(STATUS_KEY, json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to refresh status cache", e);
        }
    }

    public List<StatusEntity> getStatusesFromCache() {
        try {
            String json = redisTemplate.opsForValue().get(STATUS_KEY);

            if (json == null) {
                throw new IllegalStateException("Status cache not found in Redis");
            }

            return objectMapper.readValue(
                    json,
                    new TypeReference<List<StatusEntity>>() {
                    }
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read statuses from cache", e);
        }
    }

    public void refreshProductCache() {
        try {
            var products = productRepository.findByActiveTrue();
            String json = objectMapper.writeValueAsString(products);
            redisTemplate.opsForValue().set(PRODUCT_KEY, json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to refresh product cache", e);
        }
    }
    public List<ProductEntity> getProductsFromCache() {
        try {
            String json = redisTemplate.opsForValue().get(PRODUCT_KEY);

            if (json == null) {
                throw new IllegalStateException("Product cache not found in Redis");
            }

            return objectMapper.readValue(
                    json,
                    new com.fasterxml.jackson.core.type.TypeReference<List<ProductEntity>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read products from cache", e);
        }
    }
}
