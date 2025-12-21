package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tier_history_records")
public class TierHistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String oldTier;

    private String newTier;

    private String reason;

    private LocalDateTime upgradedAt;

    // No-arg constructor
    public TierHistoryRecord() {
    }

    // Parameterized constructor
    public TierHistoryRecord(Long customerId, String oldTier,
                             String newTier, String reason,
                             LocalDateTime upgradedAt) {
        this.customerId = customerId;
        this.oldTier = oldTier;
        this.newTier = newTier;
        this.reason = reason;
        this.upgradedAt = upgradedAt;
    }

    @PrePersist
    public void onCreate() {
        this.upgradedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOldTier() {
        return oldTier;
    }

    public void setOldTier(String oldTier) {
        this.oldTier = oldTier;
    }

    public String getNewTier() {
        return newTier;
    }

    public void setNewTier(String newTier) {
        this.newTier = newTier;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getUpgradedAt() {
        return upgradedAt;
    }

    public void setUpgradedAt(LocalDateTime upgradedAt) {
        this.upgradedAt = upgradedAt;
    }
}
