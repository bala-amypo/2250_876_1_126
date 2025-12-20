package com.example.demo.controller;

import com.example.demo.entity.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService engineService;

    public TierUpgradeEngineController(TierUpgradeEngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping("/evaluate/{customerId}")
    public TierHistoryRecord evaluate(@PathVariable Long customerId) {
        return engineService.evaluateAndUpgradeTier(customerId);
    }

    @GetMapping("/history/{customerId}")
    public List<TierHistoryRecord> historyByCustomer(@PathVariable Long customerId) {
        return engineService.getHistoryByCustomer(customerId);
    }

    @GetMapping
    public List<TierHistoryRecord> allHistory() {
        return engineService.getAllHistory();
    }
}
