package com.example.demo.service;

import com.example.demo.model.VisitRecord;
import java.util.List;

public interface VisitRecordService {

    VisitRecord recordVisit(VisitRecord visitRecord);

    VisitRecord getVisitById(Long id);

    List<VisitRecord> getVisitsByCustomer(Long customerId);

    List<VisitRecord> getAllVisits();
}
