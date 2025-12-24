package com.example.demo.repository;

import com.example.demo.model.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    @Query("SELECT v FROM VisitRecord v WHERE v.customer.id = :customerId")
    List<VisitRecord> findByCustomerId(Long customerId);
    List<VisitRecord> findByVisitDateBetween(LocalDate start, LocalDate end);
}