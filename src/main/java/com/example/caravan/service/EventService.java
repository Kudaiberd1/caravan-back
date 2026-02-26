package com.example.caravan.service;

import com.example.caravan.dto.response.DepartmentHeatmapResponse;

import java.util.List;

public interface EventService {
    Long getOnLocation(String location);

    List<DepartmentHeatmapResponse> getHeatmap(String location);
}
