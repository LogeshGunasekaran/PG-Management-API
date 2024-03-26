package com.project.pgmanagement.dao;

import com.project.pgmanagement.model.Owner;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    Optional<Owner> findByMail(String name);

    boolean deleteByMail(String username);

}

