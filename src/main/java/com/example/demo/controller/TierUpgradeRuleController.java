package com.example.demo.controller;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tier-rules")
@Tag(name = "Tier Upgrade Rules")
@SecurityRequirement(name = "bearerAuth")
public class TierUpgradeRuleController {
    
    private final TierUpgradeRuleService tierUpgradeRuleService;
    
    public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }
    
    @PostMapping
    @Operation(summary = "Create upgrade rule")
    public ResponseEntity<TierUpgradeRule> createRule(@RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(tierUpgradeRuleService.createRule(rule));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update upgrade rule")
    public ResponseEntity<TierUpgradeRule> updateRule(@PathVariable Long id, @RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(tierUpgradeRuleService.updateRule(id, rule));
    }
    
    @GetMapping("/active")
    @Operation(summary = "Get active rules")
    public ResponseEntity<List<TierUpgradeRule>> getActiveRules() {
        return ResponseEntity.ok(tierUpgradeRuleService.getActiveRules());
    }
    
    @GetMapping
    @Operation(summary = "Get all rules")
    public ResponseEntity<List<TierUpgradeRule>> getAllRules() {
        return ResponseEntity.ok(tierUpgradeRuleService.getAllRules());
    }
    
    @GetMapping("/lookup")
    @Operation(summary = "Lookup rule")
    public ResponseEntity<TierUpgradeRule> getRule(@RequestParam String fromTier, @RequestParam String toTier) {
        Optional<TierUpgradeRule> rule = tierUpgradeRuleService.getRule(fromTier, toTier);
        return rule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}