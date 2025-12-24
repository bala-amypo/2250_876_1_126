package com.example.demo.service;

import com.example.demo.model.CustomerProfile;

public interface CustomerProfileService {

    CustomerProfile save(CustomerProfile customer);

    CustomerProfile findByEmail(String email);  // 🔴 NOT Optional
}
