package com.example.demo.controller;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engine")
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
    public List<TierHistoryRecord> getHistory(@PathVariable Long customerId) {
        return engineService.getHistoryByCustomer(customerId);
    }

    @GetMapping("/history")
    public List<TierHistoryRecord> getAllHistory() {
        return engineService.getAllHistory();
    }
}
