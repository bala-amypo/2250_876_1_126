    package com.example.demo.controller;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService tierUpgradeEngineService;

    // Constructor Injection
    public TierUpgradeEngineController(
            TierUpgradeEngineService tierUpgradeEngineService) {
        this.tierUpgradeEngineService = tierUpgradeEngineService;
    }

    /**
     * POST /api/tier-engine/evaluate/{customerId}
     */
    @Operation(summary = "Evaluate and upgrade customer tier")
    @PostMapping("/evaluate/{customerId}")
    public TierHistoryRecord evaluateAndUpgradeTier(
            @PathVariable Long customerId) {

        return tierUpgradeEngineService
                .evaluateAndUpgradeTier(customerId);
    }

    /**
     * GET /api/tier-engine/history/{customerId}
     */
    @Operation(summary = "Get tier upgrade history by customer")
    @GetMapping("/history/{customerId}")
    public List<TierHistoryRecord> getHistoryByCustomer(
            @PathVariable Long customerId) {

        return tierUpgradeEngineService
                .getHistoryByCustomer(customerId);
    }

    /**
     * GET /api/tier-engine
     */
    @Operation(summary = "Get all tier upgrade history")
    @GetMapping
    public List<TierHistoryRecord> getAllHistory() {

        return tierUpgradeEngineService.getAllHistory();
    }
}
