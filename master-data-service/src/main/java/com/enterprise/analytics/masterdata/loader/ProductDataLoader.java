package com.enterprise.analytics.masterdata.loader;

import com.enterprise.analytics.masterdata.domain.entity.ProductEntity;
import com.enterprise.analytics.masterdata.repository.ProductRepository;
import com.enterprise.analytics.masterdata.service.MasterCacheService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDataLoader {
    @Bean
    CommandLineRunner loadProducts(ProductRepository productRepository, MasterCacheService cacheService) {
        return args -> {

            insertIfMissing(productRepository, "FLIGHT", "Flight");
            insertIfMissing(productRepository, "HOTEL", "Hotel");
            insertIfMissing(productRepository, "CAR", "Car Rental");

            cacheService.refreshProductCache();
        };
        }

        private void insertIfMissing(
                ProductRepository repository,
                String code,
                String name
        ) {
            repository.findByCode(code)
                    .orElseGet(() ->
                            repository.save(
                                    ProductEntity.builder()
                                            .code(code)
                                            .name(name)
                                            .active(true)
                                            .build()
                            )
                    );
        }
}
