package com.example.demo.service.impl;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final PurchaseRecordRepository purchaseRecordRepository;

    // Constructor Injection
    public PurchaseRecordServiceImpl(PurchaseRecordRepository purchaseRecordRepository) {
        this.purchaseRecordRepository = purchaseRecordRepository;
    }

    @Override
    public PurchaseRecord recordPurchase(PurchaseRecord purchaseRecord) {
        if (purchaseRecord.getAmount() == null || purchaseRecord.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return purchaseRecordRepository.save(purchaseRecord);
    }

    @Override
    public PurchaseRecord getPurchaseById(Long id) {
        return purchaseRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Purchase record not found"));
    }

    @Override
    public List<PurchaseRecord> getPurchasesByCustomer(Long customerId) {
        return purchaseRecordRepository.findByCustomerId(customerId);
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        return purchaseRecordRepository.findAll();
    }
}
