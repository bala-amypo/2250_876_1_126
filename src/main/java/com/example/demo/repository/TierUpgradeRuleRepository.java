package com.example.demo.repository;

import com.example.demo.model.TierUpgradeRule;

import java.util.List;
import java.util.Optional;

public interface TierUpgradeRuleRepository {

    TierUpgradeRule save(TierUpgradeRule rule);

    Optional<TierUpgradeRule> findById(Long id);

    Optional<TierUpgradeRule> findRule(String fromTier, String toTier);

    List<TierUpgradeRule> findActiveRules();

    List<TierUpgradeRule> findAll();
}
