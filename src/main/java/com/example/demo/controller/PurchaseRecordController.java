package com.example.demo.controller;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseRecordController {

    private final PurchaseRecordService purchaseService;

    public PurchaseRecordController(PurchaseRecordService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public PurchaseRecord recordPurchase(@RequestBody PurchaseRecord purchase) {
        return purchaseService.recordPurchase(purchase);
    }

    @GetMapping("/customer/{id}")
    public List<PurchaseRecord> getPurchasesByCustomer(@PathVariable Long id) {
        return purchaseService.getPurchasesByCustomer(id);
    }

    @GetMapping
    public List<PurchaseRecord> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}
