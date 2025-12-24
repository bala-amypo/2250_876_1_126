package com.example.demo.service;

import com.example.demo.model.CustomerProfile;

public interface CustomerProfileService {

    CustomerProfile getById(Long id);

    CustomerProfile save(CustomerProfile customer);

    CustomerProfile upgradeTier(Long id, String tier);

    CustomerProfile updateStatus(Long id, boolean status);
}
