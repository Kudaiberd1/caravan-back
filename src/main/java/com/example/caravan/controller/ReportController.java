package com.example.caravan.controller;

import com.example.caravan.dto.response.ReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @GetMapping("/")
    public ResponseEntity<List<ReportResponse>> getReports() {

    }
}
