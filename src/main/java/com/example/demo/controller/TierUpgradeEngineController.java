package com.example.demo.controller;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine")
@SecurityRequirement(name = "bearerAuth")
public class TierUpgradeEngineController {
    
    private final TierUpgradeEngineService tierUpgradeEngineService;
    
    public TierUpgradeEngineController(TierUpgradeEngineService tierUpgradeEngineService) {
        this.tierUpgradeEngineService = tierUpgradeEngineService;
    }
    
    @PostMapping("/evaluate/{customerId}")
    @Operation(summary = "Evaluate tier upgrade")
    public ResponseEntity<TierHistoryRecord> evaluateTier(@PathVariable Long customerId) {
        TierHistoryRecord result = tierUpgradeEngineService.evaluateAndUpgradeTier(customerId);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/history/{customerId}")
    @Operation(summary = "Get tier history")
    public ResponseEntity<List<TierHistoryRecord>> getHistory(@PathVariable Long customerId) {
        return ResponseEntity.ok(tierUpgradeEngineService.getHistoryByCustomer(customerId));
    }
    
    @GetMapping
    @Operation(summary = "Get all history")
    public ResponseEntity<List<TierHistoryRecord>> getAllHistory() {
        return ResponseEntity.ok(tierUpgradeEngineService.getAllHistory());
    }
}