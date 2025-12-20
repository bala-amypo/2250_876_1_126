package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private LocalDate purchaseDate;

    private String storeLocation;
}
