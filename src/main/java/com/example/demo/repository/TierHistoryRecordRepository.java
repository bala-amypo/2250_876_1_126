package com.example.demo.repository;

import com.example.demo.model.TierHistoryRecord;

import java.util.List;

public interface TierHistoryRecordRepository {

    TierHistoryRecord save(TierHistoryRecord history);

    List<TierHistoryRecord> findByCustomerId(Long customerId);

    List<TierHistoryRecord> findAll();
}
