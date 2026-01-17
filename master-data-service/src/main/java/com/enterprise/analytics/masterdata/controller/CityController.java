package com.enterprise.analytics.masterdata.controller;

import com.enterprise.analytics.masterdata.domain.entity.CityEntity;
import com.enterprise.analytics.masterdata.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/countries/{countryCode}/cities")
    public List<CityEntity> getCitiesByCountry(@PathVariable String countryCode) {
        return cityService.getCitiesByCountry(countryCode);
    }

}
