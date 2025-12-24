package com.example.demo.service;

import com.example.demo.model.CustomerProfile;
import java.util.List;

public interface CustomerProfileService {

    CustomerProfile createCustomer(CustomerProfile customer);

    CustomerProfile getCustomerById(Long id);

    List<CustomerProfile> getAllCustomers();

    CustomerProfile findByCustomerId(String customerId);

    CustomerProfile updateTier(Long id, String tier);
}
