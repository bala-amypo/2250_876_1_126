package com.example.demo.security;

import com.example.demo.service.CustomerProfileService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileService customerProfileService;

    public CustomUserDetailsService(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            var customer = customerProfileService.findByEmail(email);
            return new User(customer.getEmail(), "password", new ArrayList<>());
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
    }
}