package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secretKey;
    private final long expirationMillis;

    public JwtUtil(
            @Value("${jwt.secret:myVerySecretKeyForJWTTokenGeneration12345678901234567890}") String secretKey,
            @Value("${jwt.expiration:86400000}") long expirationMillis) {
        this.secretKey = secretKey;
        this.expirationMillis = expirationMillis;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long customerId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customerId);
        claims.put("email", email);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) throws JwtException, ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return validateToken(token).get("email", String.class);
    }

    public Long extractCustomerId(String token) {
        Object customerIdObj = validateToken(token).get("customerId");
        if (customerIdObj instanceof Integer) {
            return ((Integer) customerIdObj).longValue();
        }
        return (Long) customerIdObj;
    }

    public String extractRole(String token) {
        return validateToken(token).get("role", String.class);
    }
}