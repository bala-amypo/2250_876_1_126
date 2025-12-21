package com.example.demo.controller;

import com.example.demo.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService engineService;

    public TierUpgradeEngineController(
            TierUpgradeEngineService engineService) {
        this.engineService = engineService;
    }

    @Operation(summary = "Evaluate and upgrade customer tier")
    @PostMapping("/evaluate/{customerId}")
    public String evaluateTier(
            @PathVariable Long customerId) {

        return engineService.evaluateAndUpgradeTier(customerId);
    }
}
