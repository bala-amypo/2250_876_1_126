package com.example.demo.model;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitRecord {

    private Long id;
    private CustomerProfile customer;
    private String channel;
    private LocalDate visitDate;
}
