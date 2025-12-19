package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name= "purchase_records")
public class PurchaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private double amount;
    private LocalDate purchaseDate;
    private String storeLocation;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public PurchaseRecord(Long id, Long customerId, double amount, LocalDate purchaseDate, String storeLocation) {
         if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.storeLocation = storeLocation;
    }

    public PurchaseRecord() {
    }
}
