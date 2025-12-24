package com.example.demo.service;

import com.example.demo.model.PurchaseRecord;
import java.util.List;

public interface PurchaseRecordService {

    PurchaseRecord savePurchase(PurchaseRecord record);

    List<PurchaseRecord> getAllPurchases();

    PurchaseRecord getPurchaseById(Long id);
}
