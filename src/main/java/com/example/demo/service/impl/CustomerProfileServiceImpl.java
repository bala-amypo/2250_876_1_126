package com.example.demo.service.impl;

import com.example.demo.model.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository repository;

    @Override
    public CustomerProfile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public CustomerProfile save(CustomerProfile customer) {
        return repository.save(customer);
    }

    @Override
    public CustomerProfile upgradeTier(Long id, String tier) {
        CustomerProfile customer = getById(id);
        customer.setCurrentTier(tier);   // ✅ FIXED
        return repository.save(customer);
    }

    // ✅ THIS METHOD WAS MISSING — NOW FIXED
    @Override
    public CustomerProfile updateStatus(Long id, boolean status) {
        CustomerProfile customer = getById(id);
        customer.setActive(status);
        return repository.save(customer);
    }
}
