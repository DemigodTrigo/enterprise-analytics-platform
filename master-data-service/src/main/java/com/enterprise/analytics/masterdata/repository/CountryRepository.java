package com.enterprise.analytics.masterdata.repository;

import com.enterprise.analytics.masterdata.domain.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    Optional<CountryEntity> findByCode(String code);
    List<CountryEntity> findByActiveTrue();

}
