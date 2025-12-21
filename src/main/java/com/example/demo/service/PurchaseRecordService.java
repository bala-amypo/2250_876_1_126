package com.example.demo.service;

import com.example.demo.model.PurchaseRecord;

import java.util.List;

public interface PurchaseRecordService {

    PurchaseRecord recordPurchase(PurchaseRecord purchaseRecord);

    PurchaseRecord getPurchaseById(Long id);

    List<PurchaseRecord> getPurchasesByCustomer(Long customerId);

    List<PurchaseRecord> getAllPurchases();
}
