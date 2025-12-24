package com.example.demo.controller;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseRecordController {

    private final PurchaseRecordService service;

    public PurchaseRecordController(PurchaseRecordService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseRecord save(@RequestBody PurchaseRecord record) {
        return service.savePurchase(record);
    }

    @GetMapping
    public List<PurchaseRecord> getAll() {
        return service.getAllPurchases();
    }

    @GetMapping("/{id}")
    public PurchaseRecord getById(@PathVariable Long id) {
        return service.getPurchaseById(id);
    }
}
