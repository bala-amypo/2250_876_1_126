// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class JwtUtil {
    
//     private final String secretKey;
//     private final long expirationMillis;
    
//     public JwtUtil(@Value("${jwt.secret:mySecretKeyForJWTTokenGenerationAndValidation12345}") String secretKey,
//                    @Value("${jwt.expiration:86400000}") long expirationMillis) {
//         this.secretKey = secretKey;
//         this.expirationMillis = expirationMillis;
//     }
    
//     public String generateToken(Long customerId, String email, String role) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("customerId", customerId);
//         claims.put("email", email);
//         claims.put("role", role);
        
//         SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        
//         return Jwts.builder()
//                 .claims(claims)
//                 .subject(email)
//                 .issuedAt(new Date())
//                 .expiration(new Date(System.currentTimeMillis() + expirationMillis))
//                 .signWith(key)
//                 .compact();
//     }
    
//     public Claims validateToken(String token) throws JwtException {
//         SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//         return Jwts.parser()
//                 .verifyWith(key)
//                 .build()
//                 .parseSignedClaims(token)
//                 .getPayload();
//     }
    
//     public String extractEmail(String token) {
//         return validateToken(token).get("email", String.class);
//     }
    
//     public Long extractCustomerId(String token) {
//         Object customerIdObj = validateToken(token).get("customerId");
//         if (customerIdObj instanceof Integer) {
//             return ((Integer) customerIdObj).longValue();
//         }
//         return (Long) customerIdObj;
//     }
    
//     public String extractRole(String token) {
//         return validateToken(token).get("role", String.class);
//     }
// }