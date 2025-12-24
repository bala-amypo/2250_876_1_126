package com.example.demo.service;

import com.example.demo.model.CustomerProfile;
import java.util.List;
import java.util.Optional;

public interface CustomerProfileService {
    CustomerProfile createCustomer(CustomerProfile customer);
    CustomerProfile getCustomerById(Long id);
    Optional<CustomerProfile> findByCustomerId(String customerId);
    CustomerProfile findByEmail(String email);
    List<CustomerProfile> getAllCustomers();
    CustomerProfile updateTier(Long id, String tier);
    CustomerProfile updateStatus(Long id, boolean active);
}