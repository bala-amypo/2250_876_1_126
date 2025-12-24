package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Double amount;

    private LocalDateTime createdAt;   // 🔴 MUST be LocalDateTime
}
