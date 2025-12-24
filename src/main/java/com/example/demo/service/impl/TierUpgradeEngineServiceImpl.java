package com.example.demo.service.impl;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;

import java.util.List;

public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    public TierUpgradeEngineServiceImpl() {}

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return List.of();
    }
}
