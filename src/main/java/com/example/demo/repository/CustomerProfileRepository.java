package com.example.demo.repository;

import com.example.demo.model.CustomerProfile;

import java.util.List;
import java.util.Optional;

public interface CustomerProfileRepository {

    CustomerProfile save(CustomerProfile customer);

    Optional<CustomerProfile> findById(Long id);

    Optional<CustomerProfile> findByCustomerId(String customerId);

    List<CustomerProfile> findAll();

    void deleteById(Long id);
}
