package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    
    public String generateToken(Long customerId, String email, String role) {
        return "dummy-jwt-token-" + customerId;
    }
    
    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy-jwt-token");
    }
    
    public String extractEmail(String token) {
        return "test@example.com";
    }
    
    public Long extractCustomerId(String token) {
        return 1L;
    }
    
    public String extractRole(String token) {
        return "USER";
    }
}