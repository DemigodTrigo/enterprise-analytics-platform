package com.enterprise.analytics.masterdata.repository;

import com.enterprise.analytics.masterdata.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>{

    List<CityEntity > findByCountryCodeAndActiveTrue(String countryCode);
}
