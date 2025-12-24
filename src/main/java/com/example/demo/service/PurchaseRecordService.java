package com.example.demo.service;

import com.example.demo.model.PurchaseRecord;

public interface PurchaseRecordService {

    PurchaseRecord save(PurchaseRecord record);

    PurchaseRecord getPurchaseById(Long id);   // 🔴 REQUIRED BY TEST
}
