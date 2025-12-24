package com.example.demo.service.impl;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;

import java.util.*;

public class VisitRecordServiceImpl implements VisitRecordService {

    private final List<VisitRecord> visits = new ArrayList<>();

    public VisitRecordServiceImpl() {}

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {

        if (!List.of("STORE", "APP", "WEB").contains(visit.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }

        visit.setId((long) (visits.size() + 1));
        visits.add(visit);
        return visit;
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visits;
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return visits;
    }

    @Override
    public Optional<VisitRecord> getVisitById(Long id) {
        return Optional.empty();
    }
}
