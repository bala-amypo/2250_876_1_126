// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.security.JwtUtil;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     private final AuthenticationManager authenticationManager;
//     private final JwtUtil jwtUtil;

//     @Autowired
//     public AuthController(AuthenticationManager authenticationManager,
//                           JwtUtil jwtUtil) {
//         this.authenticationManager = authenticationManager;
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         request.getUsername(),
//                         request.getPassword()
//                 )
//         );

//         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//         String token = jwtUtil.generateToken(userDetails.getUsername());

//         AuthResponse response = new AuthResponse();
//         response.setToken(token);

//         return ResponseEntity.ok(response);
//     }
// }
