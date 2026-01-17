package com.enterprise.analytics.auth.repository;

import com.enterprise.analytics.auth.domain.entity.RoleEntity;
import com.enterprise.analytics.auth.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String username);
}
