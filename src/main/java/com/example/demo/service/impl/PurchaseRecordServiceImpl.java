package com.example.demo.service.impl;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final List<PurchaseRecord> purchases = new ArrayList<>();
    private long id = 1;

    @Override
    public PurchaseRecord recordPurchase(PurchaseRecord purchase) {
        if (purchase.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        purchase.setId(id++);
        purchases.add(purchase);
        return purchase;
    }

    @Override
    public List<PurchaseRecord> getPurchasesByCustomer(Long customerId) {
        return purchases.stream()
                .filter(p -> p.getCustomer() != null && Objects.equals(p.getCustomer().getId(), customerId))
                .toList();
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        return new ArrayList<>(purchases);
    }

    @Override
    public Optional<PurchaseRecord> getPurchaseById(Long id) {
        return purchases.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }
}