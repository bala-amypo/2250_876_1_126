package com.example.demo.service.impl;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final Map<Long, CustomerProfile> store = new HashMap<>();
    private long id = 1;

    @Override
    public CustomerProfile createCustomer(CustomerProfile customer) {
        if (customer.getCustomerId() != null) {
            Optional<CustomerProfile> existing = findByCustomerId(customer.getCustomerId());
            if (existing.isPresent()) {
                throw new IllegalArgumentException("Customer ID already exists");
            }
        }
        
        customer.setId(id++);
        if (customer.getCurrentTier() == null) {
            customer.setCurrentTier("BRONZE");
        }
        customer.setCreatedAt(LocalDateTime.now());
        store.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public CustomerProfile getCustomerById(Long id) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException("Customer not found");
        }
        return store.get(id);
    }

    @Override
    public Optional<CustomerProfile> findByCustomerId(String customerId) {
        return store.values().stream()
                .filter(c -> customerId.equals(c.getCustomerId()))
                .findFirst();
    }

    @Override
    public CustomerProfile findByEmail(String email) {
        return store.values().stream()
                .filter(c -> email.equals(c.getEmail()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public List<CustomerProfile> getAllCustomers() {
        return new ArrayList<>(store.values());
    }

    @Override
    public CustomerProfile updateTier(Long id, String tier) {
        CustomerProfile customer = getCustomerById(id);
        customer.setCurrentTier(tier);
        return customer;
    }

    @Override
    public CustomerProfile updateStatus(Long id, boolean active) {
        CustomerProfile customer = getCustomerById(id);
        customer.setActive(active);
        return customer;
    }
}