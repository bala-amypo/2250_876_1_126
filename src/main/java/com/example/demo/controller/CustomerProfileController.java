package com.example.demo.controller;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerProfileController {

    private final CustomerProfileService customerService;

    @PostMapping
    public CustomerProfile createCustomer(@RequestBody CustomerProfile customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerProfile getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerProfile> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/by-customer-id/{customerId}")
    public CustomerProfile getByCustomerId(@PathVariable String customerId) {
        return customerService.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{id}/tier/{tier}")
    public CustomerProfile updateTier(
            @PathVariable Long id,
            @PathVariable String tier) {
        return customerService.updateTier(id, tier);
    }
}
