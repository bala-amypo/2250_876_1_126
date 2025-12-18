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

    @Column(unique=True)
    private String fromTier;
}