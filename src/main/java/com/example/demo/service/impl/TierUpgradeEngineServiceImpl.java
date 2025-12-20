package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    public TierUpgradeEngineServiceImpl(
            CustomerProfileRepository customerRepo,
            PurchaseRecordRepository purchaseRepo,
            VisitRecordRepository visitRepo,
            TierUpgradeRuleRepository ruleRepo,
            TierHistoryRecordRepository historyRepo) {

        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
        this.visitRepo = visitRepo;
        this.ruleRepo = ruleRepo;
        this.historyRepo = historyRepo;
    }

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {

        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        double totalSpend = purchaseRepo.findByCustomerId(customerId)
                .stream().mapToDouble(PurchaseRecord::getAmount).sum();

        int totalVisits = visitRepo.findByCustomerId(customerId).size();

        String currentTier = customer.getCurrentTier();

        List<TierUpgradeRule> rules = ruleRepo.findByActiveTrue();

        for (TierUpgradeRule rule : rules) {

            if (rule.getFromTier().equals(currentTier)
                    && totalSpend >= rule.getMinSpend()
                    && totalVisits >= rule.getMinVisits()) {

                String oldTier = customer.getCurrentTier();
                customer.setCurrentTier(rule.getToTier());
                customerRepo.save(customer);

                TierHistoryRecord history = new TierHistoryRecord();
                history.setCustomerId(customerId);
                history.setOldTier(oldTier);
                history.setNewTier(rule.getToTier());
                history.setReason("Upgrade criteria met");

                return historyRepo.save(history);
            }
        }

        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return historyRepo.findByCustomerId(customerId);
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return historyRepo.findAll();
    }
}
