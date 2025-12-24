package com.example.demo.service.impl;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {

    private final List<TierUpgradeRule> rules = new ArrayList<>();
    private long id = 1;

    @Override
    public TierUpgradeRule createRule(TierUpgradeRule rule) {
        if (rule.getMinSpend() < 0 || rule.getMinVisits() < 0) {
            throw new IllegalArgumentException("Min spend and visits must be >= 0");
        }
        rule.setId(id++);
        if (rule.getActive() == null) {
            rule.setActive(true);
        }
        rules.add(rule);
        return rule;
    }

    @Override
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule) {
        TierUpgradeRule rule = rules.stream()
                .filter(r -> Objects.equals(r.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
        
        rule.setFromTier(updatedRule.getFromTier());
        rule.setToTier(updatedRule.getToTier());
        rule.setMinSpend(updatedRule.getMinSpend());
        rule.setMinVisits(updatedRule.getMinVisits());
        rule.setActive(updatedRule.getActive());
        return rule;
    }

    @Override
    public List<TierUpgradeRule> getActiveRules() {
        return rules.stream()
                .filter(r -> Boolean.TRUE.equals(r.getActive()))
                .toList();
    }

    @Override
    public Optional<TierUpgradeRule> getRule(String fromTier, String toTier) {
        return rules.stream()
                .filter(r -> Objects.equals(r.getFromTier(), fromTier) && Objects.equals(r.getToTier(), toTier))
                .findFirst();
    }

    @Override
    public List<TierUpgradeRule> getAllRules() {
        return new ArrayList<>(rules);
    }
}