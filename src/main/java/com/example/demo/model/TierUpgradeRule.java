package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tier_upgrade_rules", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"fromTier", "toTier"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TierUpgradeRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String fromTier;
    
    @Column(nullable = false)
    private String toTier;
    
    @Column(nullable = false)
    private Double minSpend;
    
    @Column(nullable = false)
    private Integer minVisits;
    
    @Column(nullable = false)
    private Boolean active = true;
}
