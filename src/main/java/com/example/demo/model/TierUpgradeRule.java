// package com.example.demo.model;
// import jakarta.persistence.*;
// import lombok.*;
// import java.time.LocalDateTime;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class TierUpgradeRule{
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique=true)
//     private String fromTier;

//     @Column(unique=true)
//     private String totier;

//     private double minSpend;
//     private int minVisits;
//     private Boolean active;

//     public void setFromTier(String fromTier) {
//         if (amount <= 0) {
//             throw new IllegalArgumentException("Amount must be greater than 0");
//         }
//         this.fromTier = fromTier;
//     }
//     public void setToTier(String toTier) {
//         if (amount <= 0) {
//             throw new IllegalArgumentException("Amount must be greater than 0");
//         }
//         this.toTier = totier;
//     }

// }