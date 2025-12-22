package com.example.demo.service.impl;

import com.example.demo.model.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordRepository visitRecordRepository;

    public VisitRecordServiceImpl(VisitRecordRepository visitRecordRepository) {
        this.visitRecordRepository = visitRecordRepository;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visitRecord) {
        String channel = visitRecord.getChannel();
        if (!( "STORE".equalsIgnoreCase(channel)
                || "APP".equalsIgnoreCase(channel)
                || "WEB".equalsIgnoreCase(channel))) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return visitRecordRepository.save(visitRecord);
    }

    @Override
    public VisitRecord getVisitById(Long id) {
        return visitRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visitRecordRepository.findByCustomerId(customerId);
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return visitRecordRepository.findAll();
    }
}
