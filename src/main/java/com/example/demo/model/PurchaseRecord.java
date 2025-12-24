package com.example.demo.model;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecord {

    private Long id;
    private CustomerProfile customer;
    private Double amount;
    private LocalDate purchaseDate;
    private String storeLocation;
}
