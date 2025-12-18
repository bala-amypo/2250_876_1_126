package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitRecord{
    @Id
    @GeneratedValues(strategy =G)
    private long id;

}