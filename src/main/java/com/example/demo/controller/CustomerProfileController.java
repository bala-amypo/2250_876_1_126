package com.example.demo.controller;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Profiles")
@SecurityRequirement(name = "bearerAuth")
public class CustomerProfileController {
    
    private final CustomerProfileService customerProfileService;
    
    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }
    
    @PostMapping
    @Operation(summary = "Create customer")
    public ResponseEntity<CustomerProfile> createCustomer(@RequestBody CustomerProfile customer) {
        return ResponseEntity.ok(customerProfileService.createCustomer(customer));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID")
    public ResponseEntity<CustomerProfile> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerProfileService.getCustomerById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all customers")
    public ResponseEntity<List<CustomerProfile>> getAllCustomers() {
        return ResponseEntity.ok(customerProfileService.getAllCustomers());
    }
    
    @PutMapping("/{id}/tier")
    @Operation(summary = "Update customer tier")
    public ResponseEntity<CustomerProfile> updateTier(@PathVariable Long id, @RequestParam String newTier) {
        return ResponseEntity.ok(customerProfileService.updateTier(id, newTier));
    }
    
    @GetMapping("/lookup/{customerId}")
    @Operation(summary = "Find customer by customer ID")
    public ResponseEntity<CustomerProfile> findByCustomerId(@PathVariable String customerId) {
        Optional<CustomerProfile> customer = customerProfileService.findByCustomerId(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}