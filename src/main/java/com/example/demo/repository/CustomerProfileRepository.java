package com.example.demo.repository;

import com.example.demo.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository
        extends JpaRepository<CustomerProfile, Long> {

    CustomerProfile findByEmail(String email); // 🔴 EXACT match
}
