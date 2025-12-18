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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private LocalDate visitDate;

    private String channel;

    public void setChannel(String channel){
            if(channel != STORE && channel != APP && channel != WEB){
                throw new IllegalArgumentException("Invalid Channel");
            }
        this.channel=channel;  
    }


}