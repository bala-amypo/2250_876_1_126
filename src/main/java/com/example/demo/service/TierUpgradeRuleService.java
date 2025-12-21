package com.example.demo.service;

import com.example.demo.model.TierUpgradeRule;

import java.util.List;

public interface TierUpgradeRuleService {

    TierUpgradeRule createRule(TierUpgradeRule rule);

    TierUpgradeRule updateRule(Long id, TierUpgradeRule rule);

    TierUpgradeRule getRule(String fromTier, String toTier);

    List<TierUpgradeRule> getActiveRules();

    List<TierUpgradeRule> getAllRules();
}
