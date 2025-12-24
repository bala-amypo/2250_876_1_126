package com.example.demo.service;

import com.example.demo.model.CustomerProfile;

import java.util.Optional;

public interface CustomerProfileService {

    CustomerProfile save(CustomerProfile customer);

    Optional<CustomerProfile> findByEmail(String email);
}
