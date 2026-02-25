package com.example.caravan.controller;

import com.example.caravan.dto.request.ReportRequest;
import com.example.caravan.dto.response.ReportResponse;
import com.example.caravan.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping()
    public ResponseEntity<List<ReportResponse>> getReports() {
        List<ReportResponse> reports = reportService.getReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping()
    public ResponseEntity<Void> postResponse(@RequestBody ReportRequest  reportRequest) {
        reportService.postReport(reportRequest);
        return ResponseEntity.ok().build();
    }
}
