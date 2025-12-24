package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerProfileService customerService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor Injection
    public AuthController(CustomerProfileService customerService,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        CustomerProfile customer = new CustomerProfile();
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customer.setRole(request.getRole());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setCurrentTier("BRONZE");
        customer.setActive(true);

        CustomerProfile savedCustomer = customerService.createCustomer(customer);

        return ResponseEntity.ok(savedCustomer);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // ✅ FIXED OPTIONAL ISSUE
        CustomerProfile customer = customerService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                customer.getId(),
                customer.getEmail(),
                customer.getRole()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("customerId", customer.getId());
        response.put("email", customer.getEmail());
        response.put("role", customer.getRole());

        return ResponseEntity.ok(response);
    }
}
