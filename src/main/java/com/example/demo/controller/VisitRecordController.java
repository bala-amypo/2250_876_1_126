package com.example.demo.controller;

import com.example.demo.entity.VisitRecord;
import com.example.demo.service.VisitRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitRecordController {

    private final VisitRecordService visitService;

    public VisitRecordController(VisitRecordService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public VisitRecord create(@RequestBody VisitRecord visit) {
        return visitService.recordVisit(visit);
    }

    @GetMapping("/customer/{customerId}")
    public List<VisitRecord> byCustomer(@PathVariable Long customerId) {
        return visitService.getVisitsByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public VisitRecord getById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @GetMapping
    public List<VisitRecord> getAll() {
        return visitService.getAllVisits();
    }
}
