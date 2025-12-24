package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final CustomerProfileService customerProfileService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerProfileService customerProfileService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.customerProfileService = customerProfileService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Operation(summary = "Register new customer")
    public ResponseEntity<ApiResponse<CustomerProfile>> register(@RequestBody RegisterRequest request) {
        CustomerProfile customer = new CustomerProfile(
            request.getEmail(),
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            "BRONZE",
            true,
            LocalDateTime.now()
        );
        
        CustomerProfile created = customerProfileService.createCustomer(customer);
        return ResponseEntity.ok(new ApiResponse<>(true, "Customer registered successfully", created));
    }

    @PostMapping("/login")
    @Operation(summary = "Login customer")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginRequest request) {
        CustomerProfile customer = customerProfileService.findByEmail(request.getEmail());
        
        String token = jwtUtil.generateToken(customer.getId(), customer.getEmail(), "USER");
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("customer", customer);
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", response));
    }
}