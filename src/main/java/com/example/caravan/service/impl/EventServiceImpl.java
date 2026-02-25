package com.example.caravan.service.impl;

import com.example.caravan.dto.response.DepartmentHeatmapDto;
import com.example.caravan.mapper.EventMapper;
import com.example.caravan.repository.EventRepository;
import com.example.caravan.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Long getOnLocation(String location) {
        return eventRepository.countEmployeesOnSiteToday(location);
    }

    @Override
    public List<DepartmentHeatmapDto> getHeatmap(String location) {
        var heatmap = eventRepository.onSiteHeatmapByDepartmentToday(location);
        return eventMapper.toDtoList(heatmap);
    }
}
