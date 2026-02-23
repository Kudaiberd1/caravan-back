package com.example.caravan.controller;

import com.example.caravan.dto.response.ReportResponse;
import com.example.caravan.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getReports() {
        List<ReportResponse> reports = reportService.getReports();
        return ResponseEntity.ok(reports);
    }
}
