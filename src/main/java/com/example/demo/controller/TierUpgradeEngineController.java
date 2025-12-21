package com.example.demo.controller;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine", description = "Evaluate and manage tier upgrades")
public class TierUpgradeEngineController {
    
    private final TierUpgradeEngineService tierUpgradeEngineService;
    
    public TierUpgradeEngineController(TierUpgradeEngineService tierUpgradeEngineService) {
        this.tierUpgradeEngineService = tierUpgradeEngineService;
    }
    
    @PostMapping("/evaluate/{customerId}")
    @Operation(summary = "Evaluate tier upgrade", description = "Evaluate and execute tier upgrade for a customer")
    public ResponseEntity<TierHistoryRecord> evaluateAndUpgradeTier(
            @Parameter(description = "Customer ID") @PathVariable Long customerId) {
        TierHistoryRecord result = tierUpgradeEngineService.evaluateAndUpgradeTier(customerId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(null);
        }
    }
    
    @GetMapping("/history/{customerId}")
    @Operation(summary = "Get customer history", description = "Retrieve tier upgrade history for a customer")
    public ResponseEntity<List<TierHistoryRecord>> getHistoryByCustomer(
            @Parameter(description = "Customer ID") @PathVariable Long customerId) {
        return ResponseEntity.ok(tierUpgradeEngineService.getHistoryByCustomer(customerId));
    }
    
    @GetMapping
    @Operation(summary = "Get all history", description = "Retrieve all tier upgrade history records")
    public ResponseEntity<List<TierHistoryRecord>> getAllHistory() {
        return ResponseEntity.ok(tierUpgradeEngineService.getAllHistory());
    }
}