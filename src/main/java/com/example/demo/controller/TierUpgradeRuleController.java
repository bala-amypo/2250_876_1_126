package com.example.demo.controller;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-rules")
@Tag(name = "Tier Upgrade Rules", description = "CRUD APIs for Tier Upgrade Rules")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService tierUpgradeRuleService;

    public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }

    @Operation(summary = "Create Tier Upgrade Rule")
    @PostMapping
    public TierUpgradeRule createRule(@RequestBody TierUpgradeRule rule) {
        return tierUpgradeRuleService.createRule(rule);
    }

    @Operation(summary = "Update Tier Upgrade Rule")
    @PutMapping("/{id}")
    public TierUpgradeRule updateRule(
            @PathVariable Long id,
            @RequestBody TierUpgradeRule rule) {
        return tierUpgradeRuleService.updateRule(id, rule);
    }

    @Operation(summary = "Get Active Tier Rules")
    @GetMapping("/active")
    public List<TierUpgradeRule> getActiveRules() {
        return tierUpgradeRuleService.getActiveRules();
    }

    @Operation(summary = "Get All Tier Rules")
    @GetMapping
    public List<TierUpgradeRule> getAllRules() {
        return tierUpgradeRuleService.getAllRules();
    }

    @Operation(summary = "Get Rule by From-Tier and To-Tier")
    @GetMapping("/lookup")
    public TierUpgradeRule getRule(
            @RequestParam String fromTier,
            @RequestParam String toTier) {
        return tierUpgradeRuleService.getRule(fromTier, toTier);
    }
}
