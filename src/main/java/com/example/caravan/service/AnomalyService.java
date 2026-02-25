package com.example.caravan.service;

import com.example.caravan.dto.response.AnomalyResponse;

import java.util.List;

public interface AnomalyService {
    List<AnomalyResponse> getAnomalies(String location);

    void resolveAnomaly(Integer id);
}
