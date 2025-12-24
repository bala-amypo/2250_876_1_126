package com.example.demo.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfile {

    private Long id;
    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    private String currentTier;
    private boolean active;
    private LocalDateTime createdAt;
}
