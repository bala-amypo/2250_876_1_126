package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name=customer_profiles)
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String customerId;

    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String currentTier;
    private Boolean active;
    private LocalDateTime createdAt;

    @PrePersist
    public void setDefaults() {
        if(currentTier==NULL){
            currentTier = BRONZE;
        }
        createdAt = LocalDateTime.now();
    }
}
