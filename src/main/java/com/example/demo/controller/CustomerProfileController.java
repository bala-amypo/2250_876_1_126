package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerProfileService customerProfileService;

    public CustomerController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerProfile>> createCustomer(@RequestBody CustomerProfile customer) {
        try {
            CustomerProfile created = customerProfileService.createCustomer(customer);
            return ResponseEntity.ok(new ApiResponse<>(true, "Customer created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerProfile>> getCustomer(@PathVariable Long id) {
        try {
            CustomerProfile customer = customerProfileService.getCustomerById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Customer retrieved successfully", customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerProfile>>> getAllCustomers() {
        try {
            List<CustomerProfile> customers = customerProfileService.getAllCustomers();
            return ResponseEntity.ok(new ApiResponse<>(true, "Customers retrieved successfully", customers));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}/tier")
    public ResponseEntity<ApiResponse<CustomerProfile>> updateTier(@PathVariable Long id, @RequestParam String tier) {
        try {
            CustomerProfile updated = customerProfileService.updateTier(id, tier);
            return ResponseEntity.ok(new ApiResponse<>(true, "Tier updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}