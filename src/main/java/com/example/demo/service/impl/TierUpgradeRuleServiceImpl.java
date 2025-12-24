package com.example.demo.service.impl;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;

import java.util.*;

public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {

    private final List<TierUpgradeRule> rules = new ArrayList<>();

    public TierUpgradeRuleServiceImpl() {}

    @Override
    public TierUpgradeRule createRule(TierUpgradeRule rule) {

        if (rule.getMinSpend() < 0 || rule.getMinVisits() < 0) {
            throw new IllegalArgumentException("Invalid rule values");
        }

        rule.setId((long) (rules.size() + 1));
        rules.add(rule);
        return rule;
    }

    @Override
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updated) {
        return updated;
    }

    @Override
    public List<TierUpgradeRule> getActiveRules() {
        return rules;
    }

    @Override
    public Optional<TierUpgradeRule> getRule(String fromTier, String toTier) {
        return rules.stream()
                .filter(r -> r.getFromTier().equals(fromTier)
                          && r.getToTier().equals(toTier))
                .findFirst();
    }

    @Override
    public List<TierUpgradeRule> getAllRules() {
        return rules;
    }
}
