package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.CustomerProfile;
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
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {
    
    private final CustomerProfileService customerProfileService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    public AuthController(CustomerProfileService customerProfileService, 
                         JwtUtil jwtUtil, 
                         PasswordEncoder passwordEncoder) {
        this.customerProfileService = customerProfileService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new customer", description = "Create a new customer account")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        try {
            CustomerProfile customer = new CustomerProfile();
            customer.setCustomerId(UUID.randomUUID().toString());
            customer.setEmail(request.getEmail());
            customer.setFullName(request.getFullName());
            customer.setPhone(request.getPhone());
            customer.setPassword(passwordEncoder.encode(request.getPassword()));
            customer.setRole(request.getRole() != null ? request.getRole() : "RETAIL_OPERATOR");
            customer.setCurrentTier("BRONZE");
            customer.setActive(true);
            customer.setCreatedAt(LocalDateTime.now());
            
            CustomerProfile created = customerProfileService.createCustomer(customer);
            return ResponseEntity.ok(new ApiResponse(true, "Customer registered successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate and receive JWT token")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        try {
            CustomerProfile customer = customerProfileService.findByCustomerId(request.getEmail());
            
            if (passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
                String token = jwtUtil.generateToken(customer.getId(), customer.getEmail(), customer.getRole());
                
                Map<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("customer", customer);
                
                return ResponseEntity.ok(new ApiResponse(true, "Login successful", data));
            } else {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid credentials"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid credentials"));
        }
    }
}