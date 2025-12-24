package com.example.demo.controller;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerProfileController {

    private final CustomerProfileService customerService;

    public CustomerProfileController(CustomerProfileService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerProfile createCustomer(@RequestBody CustomerProfile customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerProfile getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerProfile> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/by-customer-id/{cid}")
    public Optional<CustomerProfile> findByCustomerId(@PathVariable String cid) {
        return customerService.findByCustomerId(cid);
    }

    @PutMapping("/{id}/tier")
    public CustomerProfile updateTier(@PathVariable Long id,
                                      @RequestParam String tier) {
        return customerService.updateTier(id, tier);
    }

    @PutMapping("/{id}/status")
    public CustomerProfile updateStatus(@PathVariable Long id,
                                        @RequestParam boolean active) {
        return customerService.updateStatus(id, active);
    }
}
