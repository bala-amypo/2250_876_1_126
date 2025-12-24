package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_records")
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
    
    public PurchaseRecord() {}
    
    public PurchaseRecord(Long customerId, Double amount, LocalDate purchaseDate, String storeLocation) {
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.storeLocation = storeLocation;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public CustomerProfile getCustomer() { return customer; }
    public void setCustomer(CustomerProfile customer) { this.customer = customer; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    
    public String getStoreLocation() { return storeLocation; }
    public void setStoreLocation(String storeLocation) { this.storeLocation = storeLocation; }
}