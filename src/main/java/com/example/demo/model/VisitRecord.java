package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "visit_records")
public class VisitRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long customerId;
    
    private LocalDate visitDate;
    
    private String channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private CustomerProfile customer;

    private static final List<String> VALID_CHANNELS = Arrays.asList("STORE", "APP", "WEB");

    public VisitRecord() {}

    public VisitRecord(Long customerId, LocalDate visitDate, String channel) {
        if (channel != null && !VALID_CHANNELS.contains(channel)) {
            throw new IllegalArgumentException("Invalid channel");
        }
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.channel = channel;
    }

    @PrePersist
    @PreUpdate
    protected void validate() {
        if (channel != null && !VALID_CHANNELS.contains(channel)) {
            throw new IllegalArgumentException("Invalid channel");
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { 
        if (channel != null && !VALID_CHANNELS.contains(channel)) {
            throw new IllegalArgumentException("Invalid channel");
        }
        this.channel = channel; 
    }

    public CustomerProfile getCustomer() { return customer; }
    public void setCustomer(CustomerProfile customer) { 
        this.customer = customer;
        if (customer != null) {
            this.customerId = customer.getId();
        }
    }
}