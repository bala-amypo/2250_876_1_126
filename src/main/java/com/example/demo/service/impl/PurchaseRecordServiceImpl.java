package com.example.demo.service.impl;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ VERY IMPORTANT
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final PurchaseRecordRepository repository;

    public PurchaseRecordServiceImpl(PurchaseRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseRecord savePurchase(PurchaseRecord record) {
        return repository.save(record);
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        return repository.findAll();
    }

    @Override
    public PurchaseRecord getPurchaseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
    }
}
