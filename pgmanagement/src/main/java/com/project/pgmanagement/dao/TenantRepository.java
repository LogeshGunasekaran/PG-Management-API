package com.project.pgmanagement.dao;

import com.project.pgmanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant , Integer> {
    Optional<Tenant> findByMail(String username);
    boolean deleteByMail(String username);
}
