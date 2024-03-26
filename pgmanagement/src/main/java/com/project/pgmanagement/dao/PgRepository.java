package com.project.pgmanagement.dao;

import com.project.pgmanagement.model.Pg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PgRepository extends JpaRepository<Pg , Integer> {

    Optional<Pg> findByPgName(String pgName);
}
