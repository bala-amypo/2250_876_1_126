package com.example.demo.service.impl;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final List<VisitRecord> visits = new ArrayList<>();
    private final List<String> validChannels = List.of("STORE", "APP", "WEB");
    private long id = 1;

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        if (!validChannels.contains(visit.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }
        visit.setId(id++);
        visits.add(visit);
        return visit;
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visits.stream()
                .filter(v -> v.getCustomer() != null && Objects.equals(v.getCustomer().getId(), customerId))
                .toList();
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return new ArrayList<>(visits);
    }

    @Override
    public Optional<VisitRecord> getVisitById(Long id) {
        return visits.stream()
                .filter(v -> Objects.equals(v.getId(), id))
                .findFirst();
    }
}