package com.example.demo.repository;

import com.example.demo.model.TierHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TierHistoryRecordRepository
        extends JpaRepository<TierHistoryRecord, Long> {

    // Extra method 1
    List<TierHistoryRecord> findByCustomerId(Long customerId);

    // Extra method 2 (must match entity field: changedAt)
    List<TierHistoryRecord> findByChangedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );
}
