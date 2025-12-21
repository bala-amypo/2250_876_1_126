package com.example.demo.service.impl;

import com.example.demo.model.CustomerProfile;
import com.example.demo.model.PurchaseRecord;
import com.example.demo.model.TierHistoryRecord;
import com.example.demo.model.TierUpgradeRule;
import com.example.demo.model.VisitRecord;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.repository.TierHistoryRecordRepository;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl
        implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    // ✅ Constructor Injection ONLY
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

    // ===============================
    // Evaluate and Upgrade Tier
    // ===============================
    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {

        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() ->
                        new NoSuchElementException("Customer not found"));

        // 🔹 Calculate total spend
        List<PurchaseRecord> purchases =
                purchaseRepo.findByCustomerId(customerId);

        double totalSpend = 0;
        for (PurchaseRecord p : purchases) {
            totalSpend += p.getAmount();
        }

        // 🔹 Calculate total visits
        List<VisitRecord> visits =
                visitRepo.findByCustomerId(customerId);

        int totalVisits = visits.size();

        String currentTier = customer.getCurrentTier();

        // 🔹 Get active rules
        List<TierUpgradeRule> activeRules =
                ruleRepo.findByActiveTrue();

        for (TierUpgradeRule rule : activeRules) {

            if (rule.getFromTier().equals(currentTier)
                    && totalSpend >= rule.getMinSpend()
                    && totalVisits >= rule.getMinVisits()) {

                String oldTier = customer.getCurrentTier();
                String newTier = rule.getToTier();

                // 🔹 Update customer tier
                customer.setCurrentTier(newTier);
                customerRepo.save(customer);

                // 🔹 Save tier history
                TierHistoryRecord history =
                        new TierHistoryRecord(
                                customerId,
                                oldTier,
                                newTier,
                                "Upgrade rule matched",
                                LocalDateTime.now()
                        );

                return historyRepo.save(history);
            }
        }

        // ❗ No upgrade matched
        return null;
    }

    // ===============================
    // Get history by customer
    // ===============================
    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return historyRepo.findByCustomerId(customerId);
    }

    // ===============================
    // Get all history
    // ===============================
    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return historyRepo.findAll();
    }
}
