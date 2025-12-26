package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    @Operation(summary = "Register new customer")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            CustomerProfile customer = new CustomerProfile();
            customer.setCustomerId(request.getEmail());
            customer.setEmail(request.getEmail());
            customer.setFullName(request.getFullName());
            customer.setPhone(request.getPhone());
            customer.setPassword(passwordEncoder.encode(request.getPassword()));
            customer.setRole(request.getRole() != null ? request.getRole() : "ROLE_CUSTOMER");
            customer.setCurrentTier("BRONZE");
            customer.setActive(true);
            
            CustomerProfile created = customerProfileService.createCustomer(customer);
            return ResponseEntity.ok(new AuthResponse(true, "Registration successful", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(false, e.getMessage(), null));
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            Optional<CustomerProfile> customerOpt = customerProfileService.findByCustomerId(request.getEmail());
            if (customerOpt.isEmpty()) {
                customerOpt = customerProfileService.getAllCustomers().stream()
                        .filter(c -> c.getEmail().equals(request.getEmail()))
                        .findFirst();
            }
            
            if (customerOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(new AuthResponse(false, "Invalid credentials", null));
            }
            
            CustomerProfile customer = customerOpt.get();
            
            if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
                return ResponseEntity.badRequest().body(new AuthResponse(false, "Invalid credentials", null));
            }
            
            String token = jwtUtil.generateToken(customer.getId(), customer.getEmail(), customer.getRole());
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("customer", customer);
            
            return ResponseEntity.ok(new AuthResponse(true, "Login successful", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(false, e.getMessage(), null));
        }
    }
}
