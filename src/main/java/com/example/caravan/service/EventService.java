package com.example.caravan.service;

import com.example.caravan.dto.response.DepartmentHeatmapDto;

import java.util.List;

public interface EventService {
    Long getOnLocation(String location);

    List<DepartmentHeatmapDto> getHeatmap(String location);
}
