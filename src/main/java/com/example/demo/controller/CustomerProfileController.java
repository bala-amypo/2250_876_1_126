package com.example.demo.controller;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Profiles", description = "CRUD APIs for Customer Profiles")
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @Operation(summary = "Create Customer")
    @PostMapping
    public CustomerProfile createCustomer(@RequestBody CustomerProfile customer) {
        return customerProfileService.createCustomer(customer);
    }

    @Operation(summary = "Get Customer by ID")
    @GetMapping("/{id}")
    public CustomerProfile getCustomerById(@PathVariable Long id) {
        return customerProfileService.getCustomerById(id);
    }

    @Operation(summary = "Get All Customers")
    @GetMapping
    public List<CustomerProfile> getAllCustomers() {
        return customerProfileService.getAllCustomers();
    }

    @Operation(summary = "Update Customer")
    @PutMapping("/{id}")
    public CustomerProfile updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerProfile customer) {
        return customerProfileService.updateCustomer(id, customer);
    }

    @Operation(summary = "Delete Customer")
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerProfileService.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}
