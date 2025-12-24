package com.example.demo.controller;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerProfileService customerProfileService;

    @PostMapping("/activate/{id}")
    public String activateCustomer(@PathVariable Long id) {

        CustomerProfile customer = customerProfileService.getById(id);

        // ✅ CORRECT setter
        customer.setCurrentTier("SILVER");
        customer.setActive(true);

        customerProfileService.save(customer);
        return "Customer activated";
    }
}
