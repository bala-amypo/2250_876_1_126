package com.example.demo.controller;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitRecordController {

    private final VisitRecordService visitService;

    public VisitRecordController(VisitRecordService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public VisitRecord recordVisit(@RequestBody VisitRecord visit) {
        return visitService.recordVisit(visit);
    }

    @GetMapping("/customer/{id}")
    public List<VisitRecord> getVisitsByCustomer(@PathVariable Long id) {
        return visitService.getVisitsByCustomer(id);
    }

    @GetMapping
    public List<VisitRecord> getAllVisits() {
        return visitService.getAllVisits();
    }
}
