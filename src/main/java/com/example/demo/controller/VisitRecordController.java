package com.example.demo.controller;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visit Records", description = "CRUD APIs for Visit Records")
public class VisitRecordController {

    private final VisitRecordService visitRecordService;

    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    @Operation(summary = "Create Visit Record")
    @PostMapping
    public VisitRecord createVisit(@RequestBody VisitRecord visitRecord) {
        return visitRecordService.recordVisit(visitRecord);
    }

    @Operation(summary = "Get Visit by ID")
    @GetMapping("/{id}")
    public VisitRecord getVisitById(@PathVariable Long id) {
        return visitRecordService.getVisitById(id);
    }

    @Operation(summary = "Get Visits by Customer ID")
    @GetMapping("/customer/{customerId}")
    public List<VisitRecord> getVisitsByCustomer(@PathVariable Long customerId) {
        return visitRecordService.getVisitsByCustomer(customerId);
    }

    @Operation(summary = "Get All Visits")
    @GetMapping
    public List<VisitRecord> getAllVisits() {
        return visitRecordService.getAllVisits();
    }
}
