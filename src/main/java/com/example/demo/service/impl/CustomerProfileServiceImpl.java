package com.example.demo.service.impl;

import com.example.demo.model.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.service.CustomerProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository repository;

    public CustomerProfileServiceImpl(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerProfile createCustomer(CustomerProfile customer) {
        customer.setCreatedAt(LocalDateTime.now());
        return repository.save(customer);
    }

    @Override
    public CustomerProfile getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CustomerProfile> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public CustomerProfile findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public CustomerProfile updateTier(Long id, String tier) {
        CustomerProfile customer = getCustomerById(id);
        if (customer != null) {
            customer.setCurrentTier(tier);
            return repository.save(customer);
        }
        return null;
    }
}
