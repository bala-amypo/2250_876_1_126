package com.example.demo.repository;

import com.example.demo.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository
        extends JpaRepository<CustomerProfile, Long> {

    Optional<CustomerProfile> findByEmail(String email);

    // ✅ THIS FIXES YOUR ERROR
    Optional<CustomerProfile> findByCustomerId(String customerId);
}
