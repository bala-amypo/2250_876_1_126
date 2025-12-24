package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@CrossOrigin(origins = "*")
public class VisitController {

    private final VisitRecordService visitRecordService;

    public VisitController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VisitRecord>> recordVisit(@RequestBody VisitRecord visit) {
        try {
            VisitRecord created = visitRecordService.recordVisit(visit);
            return ResponseEntity.ok(new ApiResponse<>(true, "Visit recorded successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<VisitRecord>>> getVisitsByCustomer(@PathVariable Long customerId) {
        try {
            List<VisitRecord> visits = visitRecordService.getVisitsByCustomer(customerId);
            return ResponseEntity.ok(new ApiResponse<>(true, "Visits retrieved successfully", visits));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VisitRecord>>> getAllVisits() {
        try {
            List<VisitRecord> visits = visitRecordService.getAllVisits();
            return ResponseEntity.ok(new ApiResponse<>(true, "All visits retrieved successfully", visits));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VisitRecord>> getVisitById(@PathVariable Long id) {
        try {
            return visitRecordService.getVisitById(id)
                .map(visit -> ResponseEntity.ok(new ApiResponse<>(true, "Visit retrieved successfully", visit)))
                .orElse(ResponseEntity.badRequest().body(new ApiResponse<>(false, "Visit not found", null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}