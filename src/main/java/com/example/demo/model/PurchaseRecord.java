package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customer;
    
    @Column(nullable = false)
    private Double amount;
    
    @Column(nullable = false)
    private LocalDate purchaseDate;
    
    @Column(nullable = false)
    private String storeLocation;
}
