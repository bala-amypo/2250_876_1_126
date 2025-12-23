package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey = "customer-loyalty-secret";
    private final long expirationMillis = 86400000; // 1 day

    public String generateToken(Long customerId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("customerId", customerId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return validateToken(token).getSubject();
    }

    public Long extractCustomerId(String token) {
        return validateToken(token).get("customerId", Long.class);
    }

    public String extractRole(String token) {
        return validateToken(token).get("role", String.class);
    }
}
