package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {

    private final PurchaseRecordService purchaseRecordService;

    public PurchaseController(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PurchaseRecord>> recordPurchase(@RequestBody PurchaseRecord purchase) {
        try {
            PurchaseRecord created = purchaseRecordService.recordPurchase(purchase);
            return ResponseEntity.ok(new ApiResponse<>(true, "Purchase recorded successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<PurchaseRecord>>> getPurchasesByCustomer(@PathVariable Long customerId) {
        try {
            List<PurchaseRecord> purchases = purchaseRecordService.getPurchasesByCustomer(customerId);
            return ResponseEntity.ok(new ApiResponse<>(true, "Purchases retrieved successfully", purchases));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PurchaseRecord>>> getAllPurchases() {
        try {
            List<PurchaseRecord> purchases = purchaseRecordService.getAllPurchases();
            return ResponseEntity.ok(new ApiResponse<>(true, "All purchases retrieved successfully", purchases));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PurchaseRecord>> getPurchaseById(@PathVariable Long id) {
        try {
            return purchaseRecordService.getPurchaseById(id)
                .map(purchase -> ResponseEntity.ok(new ApiResponse<>(true, "Purchase retrieved successfully", purchase)))
                .orElse(ResponseEntity.badRequest().body(new ApiResponse<>(false, "Purchase not found", null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}