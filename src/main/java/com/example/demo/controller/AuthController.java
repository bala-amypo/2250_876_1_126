package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerProfileService customerService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerProfileService customerService,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {

        CustomerProfile customer = new CustomerProfile();
        customer.setCustomerId(request.getEmail());
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customer.setCurrentTier("BRONZE");

        CustomerProfile saved = customerService.createCustomer(customer);
        return new ApiResponse(true, "Registered successfully", saved);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {

        CustomerProfile customer =
                customerService.findByCustomerId(request.getEmail());

        String token = jwtUtil.generateToken(
                customer.getId(),
                customer.getEmail(),
                "USER"
        );

        return new ApiResponse(true, "Login successful", token);
    }
}
