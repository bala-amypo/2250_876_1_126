package com.example.demo.controller;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-rules")
@Tag(name = "Tier Upgrade Rules", description = "Manage tier upgrade rules and thresholds")
public class TierUpgradeRuleController {
    
    private final TierUpgradeRuleService tierUpgradeRuleService;
    
    public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }
    
    @PostMapping
    @Operation(summary = "Create rule", description = "Create a new tier upgrade rule")
    public ResponseEntity<TierUpgradeRule> createRule(@RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(tierUpgradeRuleService.createRule(rule));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update rule", description = "Update an existing tier upgrade rule")
    public ResponseEntity<TierUpgradeRule> updateRule(
            @Parameter(description = "Rule ID") @PathVariable Long id,
            @RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(tierUpgradeRuleService.updateRule(id, rule));
    }
    
    @GetMapping("/active")
    @Operation(summary = "Get active rules", description = "Retrieve all active tier upgrade rules")
    public ResponseEntity<List<TierUpgradeRule>> getActiveRules() {
        return ResponseEntity.ok(tierUpgradeRuleService.getActiveRules());
    }
    
    @GetMapping
    @Operation(summary = "Get all rules", description = "Retrieve all tier upgrade rules")
    public ResponseEntity<List<TierUpgradeRule>> getAllRules() {
        return ResponseEntity.ok(tierUpgradeRuleService.getAllRules());
    }
    
    @GetMapping("/lookup")
    @Operation(summary = "Lookup rule", description = "Find rule by from and to tier")
    public ResponseEntity<TierUpgradeRule> getRule(
            @Parameter(description = "From tier") @RequestParam String fromTier,
            @Parameter(description = "To tier") @RequestParam String toTier) {
        return ResponseEntity.ok(tierUpgradeRuleService.getRule(fromTier, toTier));
    }
}