package com.example.demo.service.impl;

import com.example.demo.entity.VisitRecord;
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
    public VisitRecord recordVisit(VisitRecord visit) {
        if (visit.getChannel() == null || 
            (!visit.getChannel().equals("STORE") && 
             !visit.getChannel().equals("APP") && 
             !visit.getChannel().equals("WEB"))) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return visitRecordRepository.save(visit);
    }
    
    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visitRecordRepository.findByCustomerId(customerId);
    }
    
    @Override
    public List<VisitRecord> getAllVisits() {
        return visitRecordRepository.findAll();
    }
    
    @Override
    public VisitRecord getVisitById(Long id) {
        return visitRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
}