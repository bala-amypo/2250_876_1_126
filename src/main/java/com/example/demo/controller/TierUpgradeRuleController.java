package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin(origins = "*")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService tierUpgradeRuleService;

    public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TierUpgradeRule>> createRule(@RequestBody TierUpgradeRule rule) {
        try {
            TierUpgradeRule created = tierUpgradeRuleService.createRule(rule);
            return ResponseEntity.ok(new ApiResponse<>(true, "Rule created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TierUpgradeRule>> updateRule(@PathVariable Long id, @RequestBody TierUpgradeRule rule) {
        try {
            TierUpgradeRule updated = tierUpgradeRuleService.updateRule(id, rule);
            return ResponseEntity.ok(new ApiResponse<>(true, "Rule updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<TierUpgradeRule>>> getActiveRules() {
        try {
            List<TierUpgradeRule> rules = tierUpgradeRuleService.getActiveRules();
            return ResponseEntity.ok(new ApiResponse<>(true, "Active rules retrieved successfully", rules));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TierUpgradeRule>>> getAllRules() {
        try {
            List<TierUpgradeRule> rules = tierUpgradeRuleService.getAllRules();
            return ResponseEntity.ok(new ApiResponse<>(true, "All rules retrieved successfully", rules));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/lookup")
    public ResponseEntity<ApiResponse<TierUpgradeRule>> getRule(@RequestParam String fromTier, @RequestParam String toTier) {
        try {
            return tierUpgradeRuleService.getRule(fromTier, toTier)
                .map(rule -> ResponseEntity.ok(new ApiResponse<>(true, "Rule found", rule)))
                .orElse(ResponseEntity.badRequest().body(new ApiResponse<>(false, "Rule not found", null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}