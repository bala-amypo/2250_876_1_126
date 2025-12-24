package com.example.demo.controller;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visit Records")
@SecurityRequirement(name = "bearerAuth")
public class VisitRecordController {
    
    private final VisitRecordService visitRecordService;
    
    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }
    
    @PostMapping
    @Operation(summary = "Record visit")
    public ResponseEntity<VisitRecord> recordVisit(@RequestBody VisitRecord visit) {
        return ResponseEntity.ok(visitRecordService.recordVisit(visit));
    }
    
    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get visits by customer")
    public ResponseEntity<List<VisitRecord>> getVisitsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(visitRecordService.getVisitsByCustomer(customerId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get visit by ID")
    public ResponseEntity<VisitRecord> getVisitById(@PathVariable Long id) {
        Optional<VisitRecord> visit = visitRecordService.getVisitById(id);
        return visit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Get all visits")
    public ResponseEntity<List<VisitRecord>> getAllVisits() {
        return ResponseEntity.ok(visitRecordService.getAllVisits());
    }
}