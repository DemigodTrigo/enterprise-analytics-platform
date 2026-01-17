package com.enterprise.analytics.masterdata.repository;

import com.enterprise.analytics.masterdata.domain.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

    Optional<StatusEntity> findByCode(String code);

    List<StatusEntity> findByActiveTrue();

}
