package com.example.demo.service.impl;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final PurchaseRecordRepository repository;

    public PurchaseRecordServiceImpl(PurchaseRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseRecord save(PurchaseRecord record) {
        return repository.save(record);
    }

    @Override
    public PurchaseRecord getPurchaseById(Long id) {
        return repository.findById(id).orElse(null); // 🔴 test expects PurchaseRecord
    }
}
    