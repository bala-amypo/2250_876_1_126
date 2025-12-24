package com.example.demo.repository;

import com.example.demo.model.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    @Query("SELECT p FROM PurchaseRecord p WHERE p.customer.id = :customerId")
    List<PurchaseRecord> findByCustomerId(Long customerId);
    List<PurchaseRecord> findByPurchaseDateBetween(LocalDate start, LocalDate end);
}
