package com.example.demo.controller;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rules")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService ruleService;

    public TierUpgradeRuleController(TierUpgradeRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public TierUpgradeRule createRule(@RequestBody TierUpgradeRule rule) {
        return ruleService.createRule(rule);
    }

    @GetMapping("/active")
    public List<TierUpgradeRule> getActiveRules() {
        return ruleService.getActiveRules();
    }

    @GetMapping("/{from}/{to}")
    public Optional<TierUpgradeRule> getRule(@PathVariable String from,
                                             @PathVariable String to) {
        return ruleService.getRule(from, to);
    }
}
