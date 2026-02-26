package com.example.caravan.mapper;

import com.example.caravan.dto.response.DepartmentHeatmapResponse;
import com.example.caravan.repository.EventRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "employeesOnLocation", source = "employeesCount")
    @Mapping(target = "employeesTotal", source = "totalEmployees")
    DepartmentHeatmapResponse toDto(
            EventRepository.DepartmentHeatmapRow row
    );

    List<DepartmentHeatmapResponse> toDtoList(
            List<EventRepository.DepartmentHeatmapRow> rows
    );
}