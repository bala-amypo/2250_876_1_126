package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tier_history_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TierHistoryRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long customerId;
    
    @Column(nullable = false)
    private String oldTier;
    
    @Column(nullable = false)
    private String newTier;
    
    @Column(nullable = false)
    private String reason;
    
    @Column(nullable = false)
    private LocalDateTime changedAt;
    
    @PrePersist
    protected void onCreate() {
        if (changedAt == null) {
            changedAt = LocalDateTime.now();
        }
    }
}