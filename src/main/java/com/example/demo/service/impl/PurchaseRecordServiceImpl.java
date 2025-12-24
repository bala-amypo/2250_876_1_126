package com.example.demo.service.impl;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;

import java.util.*;

public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final List<PurchaseRecord> purchases = new ArrayList<>();

    public PurchaseRecordServiceImpl() {}

    @Override
    public PurchaseRecord recordPurchase(PurchaseRecord purchase) {

        if (purchase.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        purchase.setId((long) (purchases.size() + 1));
        purchases.add(purchase);
        return purchase;
    }

    @Override
    public List<PurchaseRecord> getPurchasesByCustomer(Long customerId) {
        return purchases;
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        return purchases;
    }

    @Override
    public Optional<PurchaseRecord> getPurchaseById(Long id) {
        return Optional.empty();
    }
}
