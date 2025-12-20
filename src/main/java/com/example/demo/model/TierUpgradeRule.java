package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "tier_upgrade_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = {"fromTier", "toTier"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TierUpgradeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromTier;

    private String toTier;

    private Double minSpend;

    private Integer minVisits;

    private Boolean active = true;
}
