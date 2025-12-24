package com.example.demo.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TierHistoryRecord {

    private Long id;
    private CustomerProfile customer;
    private String fromTier;
    private String toTier;
    private LocalDateTime upgradedAt;
}
