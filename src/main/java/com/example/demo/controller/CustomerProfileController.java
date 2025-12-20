package com.example.demo.controller;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Profiles", description = "Customer profile management endpoints")
public class CustomerProfileController {
    
    private final CustomerProfileService customerProfileService;
    
    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }
    
    @PostMapping
    @Operation(summary = "Create customer", description = "Create a new customer profile")
    public ResponseEntity<CustomerProfile> createCustomer(@RequestBody CustomerProfile customer) {
        return ResponseEntity.ok(customerProfileService.createCustomer(customer));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieve a customer profile by ID")
    public ResponseEntity<CustomerProfile> getCustomerById(
            @Parameter(description = "Customer ID") @PathVariable Long id) {
        return ResponseEntity.ok(customerProfileService.getCustomerById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieve all customer profiles")
    public ResponseEntity<List<CustomerProfile>> getAllCustomers() {
        return ResponseEntity.ok(customerProfileService.getAllCustomers());
    }
    
    @PutMapping("/{id}/tier")
    @Operation(summary = "Update customer tier", description = "Update a customer's loyalty tier")
    public ResponseEntity<CustomerProfile> updateTier(
            @Parameter(description = "Customer ID") @PathVariable Long id,
            @Parameter(description = "New tier") @RequestParam String newTier) {
        return ResponseEntity.ok(customerProfileService.updateTier(id, newTier));
    }
    
    @GetMapping("/lookup/{customerId}")
    @Operation(summary = "Lookup customer", description = "Find customer by customer ID")
    public ResponseEntity<CustomerProfile> findByCustomerId(
            @Parameter(description = "Customer ID") @PathVariable String customerId) {
        return ResponseEntity.ok(customerProfileService.findByCustomerId(customerId));
    }
}