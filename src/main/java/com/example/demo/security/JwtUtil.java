package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey = "secretkey123";
    private final long expirationMillis = 3600000; // 1 hour

    public String generateToken(Long customerId, String email, String role) {

        return Jwts.builder()
                .claim("customerId", customerId)
                .claim("email", email)
                .claim("role", role)
                .setSubject(email)
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
        return validateToken(token).get("email", String.class);
    }

    public Long extractCustomerId(String token) {
        return validateToken(token).get("customerId", Long.class);
    }

    public String extractRole(String token) {
        return validateToken(token).get("role", String.class);
    }
}
