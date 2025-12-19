package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "tier_upgrade_rules" )
public class TierUpgradeRule{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String fromTier;

    @Column(unique=true)
    private String totier;

    private double minSpend;
    private int minVisits;
    private Boolean active;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFromTier() {
        return fromTier;
    }
    public void setFromTier(String fromTier) {
        this.fromTier = fromTier;
    }
    public String getTotier() {
        return totier;
    }
    public void setTotier(String totier) {
        this.totier = totier;
    }
    public double getMinSpend() {
        return minSpend;
    }
    public void setMinSpend(double minSpend) {
        this.minSpend = minSpend;
    }
    public int getMinVisits() {
        return minVisits;
    }
    public void setMinVisits(int minVisits) {
        this.minVisits = minVisits;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public TierUpgradeRule(Long id, String fromTier, String totier, double minSpend, int minVisits, Boolean active) {
        this.id = id;
        this.fromTier = fromTier;
        this.totier = totier;
        this.minSpend = minSpend;
        this.minVisits = minVisits;
        this.active = active;
    }
    public TierUpgradeRule() {
    }


}