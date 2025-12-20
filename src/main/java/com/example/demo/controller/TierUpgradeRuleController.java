package com.example.demo.controller;

import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-rules")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService ruleService;

    public TierUpgradeRuleController(TierUpgradeRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public TierUpgradeRule create(@RequestBody TierUpgradeRule rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public TierUpgradeRule update(
            @PathVariable Long id,
            @RequestBody TierUpgradeRule rule) {
        return ruleService.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<TierUpgradeRule> activeRules() {
        return ruleService.getActiveRules();
    }

    @GetMapping
    public List<TierUpgradeRule> allRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/lookup")
    public TierUpgradeRule getRule(
            @RequestParam String fromTier,
            @RequestParam String toTier) {
        return ruleService.getRule(fromTier, toTier);
    }
}
