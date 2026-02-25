package com.example.caravan.controller;

import com.example.caravan.dto.response.AnomalyResponse;
import com.example.caravan.service.AnomalyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/anomaly")
public class AnomalyController {
    private final AnomalyService anomalyService;

    @GetMapping
    public ResponseEntity<List<AnomalyResponse>> getAnomaly(@RequestParam String location) {
        List<AnomalyResponse> anomalies = anomalyService.getAnomalies(location);
        return ResponseEntity.ok(anomalies);
    }

    @PutMapping("/resolve/{id}")
    public ResponseEntity<Void> resolveAnomaly(@PathVariable Integer id) {
        anomalyService.resolveAnomaly(id);
        return ResponseEntity.ok().build();
    }
}
