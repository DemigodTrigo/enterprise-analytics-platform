package com.enterprise.analytics.masterdata.controller;

import com.enterprise.analytics.masterdata.domain.entity.ProductEntity;
import com.enterprise.analytics.masterdata.domain.entity.StatusEntity;
import com.enterprise.analytics.masterdata.response.EAJson;
import com.enterprise.analytics.masterdata.service.MasterCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master/products")
public class ProductController {
    private final MasterCacheService cacheService;

    public ProductController(MasterCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public EAJson<List<ProductEntity>> getProducts() {
        return EAJson.success(cacheService.getProductsFromCache(), "Products loaded successfully");
    }

}
