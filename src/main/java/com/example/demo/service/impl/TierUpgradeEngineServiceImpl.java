package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileService customerProfileService;
    private final PurchaseRecordService purchaseRecordService;
    private final VisitRecordService visitRecordService;
    private final TierUpgradeRuleService tierUpgradeRuleService;
    
    private final List<TierHistoryRecord> history = new ArrayList<>();
    private long id = 1;

    public TierUpgradeEngineServiceImpl(CustomerProfileService customerProfileService,
                                       PurchaseRecordService purchaseRecordService,
                                       VisitRecordService visitRecordService,
                                       TierUpgradeRuleService tierUpgradeRuleService) {
        this.customerProfileService = customerProfileService;
        this.purchaseRecordService = purchaseRecordService;
        this.visitRecordService = visitRecordService;
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        try {
            CustomerProfile customer = customerProfileService.getCustomerById(customerId);
            
            List<PurchaseRecord> purchases = purchaseRecordService.getPurchasesByCustomer(customerId);
            List<VisitRecord> visits = visitRecordService.getVisitsByCustomer(customerId);

            double totalSpend = purchases.stream().mapToDouble(PurchaseRecord::getAmount).sum();
            int totalVisits = visits.size();

            String currentTier = customer.getCurrentTier();
            List<TierUpgradeRule> activeRules = tierUpgradeRuleService.getActiveRules();

            for (TierUpgradeRule rule : activeRules) {
                if (Objects.equals(rule.getFromTier(), currentTier) &&
                    totalSpend >= rule.getMinSpend() &&
                    totalVisits >= rule.getMinVisits()) {

                    String oldTier = customer.getCurrentTier();
                    customerProfileService.updateTier(customerId, rule.getToTier());

                    TierHistoryRecord historyRecord = new TierHistoryRecord();
                    historyRecord.setId(id++);
                    historyRecord.setCustomerId(customerId);
                    historyRecord.setOldTier(oldTier);
                    historyRecord.setNewTier(rule.getToTier());
                    historyRecord.setReason("Automatic upgrade based on spend and visits");
                    historyRecord.setChangedAt(LocalDateTime.now());

                    history.add(historyRecord);
                    return historyRecord;
                }
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Customer not found");
        }

        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return history.stream()
                .filter(h -> Objects.equals(h.getCustomerId(), customerId))
                .toList();
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return new ArrayList<>(history);
    }
}