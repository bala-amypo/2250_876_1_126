package com.example.demo.service;

import com.example.demo.model.CustomerProfile;

import java.util.List;
import java.util.Optional;

public interface CustomerProfileService {

    CustomerProfile createCustomer(CustomerProfile customer);

    CustomerProfile getCustomerById(Long id);

    List<CustomerProfile> getAllCustomers();

    Optional<CustomerProfile> findByCustomerId(String customerId);

    Optional<CustomerProfile> findByEmail(String email);

    CustomerProfile updateTier(Long id, String tier);
}
