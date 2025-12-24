package com.example.demo.repository;

import com.example.demo.model.PurchaseRecord;

import java.util.List;
import java.util.Optional;

public interface PurchaseRecordRepository {

    PurchaseRecord save(PurchaseRecord purchase);

    Optional<PurchaseRecord> findById(Long id);

    List<PurchaseRecord> findByCustomerId(Long customerId);

    List<PurchaseRecord> findAll();
}
