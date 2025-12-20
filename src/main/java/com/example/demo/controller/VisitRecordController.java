package com.example.demo.controller;

import com.example.demo.entity.VisitRecord;
import com.example.demo.service.VisitRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visit Records", description = "Manage customer visit records")
public class VisitRecordController {
    
    private final VisitRecordService visitRecordService;
    
    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }
    
    @PostMapping
    @Operation(summary = "Record visit", description = "Record a new customer visit")
    public ResponseEntity<VisitRecord> recordVisit(@RequestBody VisitRecord visit) {
        return ResponseEntity.ok(visitRecordService.recordVisit(visit));
    }
    
    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get visits by customer", description = "Retrieve all visits for a customer")
    public ResponseEntity<List<VisitRecord>> getVisitsByCustomer(
            @Parameter(description = "Customer ID") @PathVariable Long customerId) {
        return ResponseEntity.ok(visitRecordService.getVisitsByCustomer(customerId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get visit by ID", description = "Retrieve visit record by ID")
    public ResponseEntity<VisitRecord> getVisitById(
            @Parameter(description = "Visit ID") @PathVariable Long id) {
        return ResponseEntity.ok(visitRecordService.getVisitById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all visits", description = "Retrieve all visit records")
    public ResponseEntity<List<VisitRecord>> getAllVisits() {
        return ResponseEntity.ok(visitRecordService.getAllVisits());
    }
}