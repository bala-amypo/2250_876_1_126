package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TierUpgradeRule{
    @Id
    @GeneratedValues (strategy=GenerationType.IDENTITY);
    private Long id;

    @Column(unique=true)
    private String fromTier;

    @Column(unique=true)
    private String totier;

    private double minSpend;
    private int minVisits;
    private Boolean active;
    

}