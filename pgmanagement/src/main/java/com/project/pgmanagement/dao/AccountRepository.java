package com.project.pgmanagement.dao;

import com.project.pgmanagement.model.constant.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account ,Integer> {

    Optional<Account> findByUsername(String username);
}

