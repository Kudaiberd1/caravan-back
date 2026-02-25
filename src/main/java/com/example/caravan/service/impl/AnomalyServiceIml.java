package com.example.caravan.service.impl;

import com.example.caravan.dto.response.AnomalyResponse;
import com.example.caravan.entity.Anomaly;
import com.example.caravan.mapper.AnomalyMapper;
import com.example.caravan.repository.AnomalyRepository;
import com.example.caravan.service.AnomalyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnomalyServiceIml implements AnomalyService {
    private final AnomalyRepository anomalyRepository;
    private final AnomalyMapper anomalyMapper;

    @Override
    public List<AnomalyResponse> getAnomalies(String location) {
        var anomalies = anomalyRepository.findTopActiveAnomalies(location);
        return anomalyMapper.toDtoList(anomalies);
    }

    @Override
    public void resolveAnomaly(Integer id) {
        Anomaly anomaly = anomalyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anomaly not found with id: " + id));

        anomaly.setIsExcused(true);
        anomalyRepository.save(anomaly);
    }
}
