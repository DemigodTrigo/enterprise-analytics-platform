package com.enterprise.analytics.masterdata.controller;

import com.enterprise.analytics.masterdata.service.MasterCacheService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/master")
public class MasterAdminController {

    private final MasterCacheService cacheService;

    public MasterAdminController(MasterCacheService cacheService) {
        this.cacheService = cacheService;
    }

   @PostMapping("/refresh")
    public String refreshMasterData() {
        cacheService.refreshCountryCache();
    return "Master data cache refreshed successfully";
    }

}
