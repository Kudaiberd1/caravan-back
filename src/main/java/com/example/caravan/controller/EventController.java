package com.example.caravan.controller;

import com.example.caravan.dto.response.DepartmentHeatmapDto;
import com.example.caravan.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/on-location")
    public ResponseEntity<Long> getEventsByLocation(@RequestParam String location) {
        Long onLocation = eventService.getOnLocation(location);
        return ResponseEntity.ok(onLocation);
    }

    @GetMapping("/heatmap")
    public ResponseEntity<List<DepartmentHeatmapDto>> getHeatmap(@RequestParam String location) {
        List<DepartmentHeatmapDto> heatmap = eventService.getHeatmap(location);
        return ResponseEntity.ok(heatmap);
    }
}
