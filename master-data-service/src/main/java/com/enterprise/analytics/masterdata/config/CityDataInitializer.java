package com.enterprise.analytics.masterdata.config;

import com.enterprise.analytics.masterdata.domain.entity.CityEntity;
import com.enterprise.analytics.masterdata.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CityDataInitializer {

    private final CityRepository cityRepository;

    @Bean
    CommandLineRunner loadCities() {
        return args -> {
            if (cityRepository.count() == 0) {
                cityRepository.saveAll(List.of(
                        CityEntity.builder().name("Delhi").countryCode("IN").active(true).build(),
                        CityEntity.builder().name("Mumbai").countryCode("IN").active(true).build(),
                        CityEntity.builder().name("New York").countryCode("US").active(true).build(),
                        CityEntity.builder().name("San Francisco").countryCode("US").active(true).build(),
                        CityEntity.builder().name("Toronto").countryCode("CA").active(true).build()
                ));
            }
        };
    }

}
