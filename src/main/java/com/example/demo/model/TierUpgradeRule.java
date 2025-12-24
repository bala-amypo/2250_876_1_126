package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TierUpgradeRule {

    private Long id;
    private String fromTier;
    private String toTier;
    private Double minSpend;
    private Integer minVisits;
    private boolean active;
}
