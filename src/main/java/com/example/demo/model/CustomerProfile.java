package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
        currentTier = "BRONZE";
        active = true;
        createdAt = LocalDateTime.now();
    }
}
