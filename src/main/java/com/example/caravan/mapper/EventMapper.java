package com.example.caravan.mapper;

import com.example.caravan.dto.response.DepartmentHeatmapDto;
import com.example.caravan.repository.EventRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "employeesOnLocation", source = "employeesCount")
    @Mapping(target = "employeesTotal", source = "totalEmployees")
    DepartmentHeatmapDto toDto(
            EventRepository.DepartmentHeatmapRow row
    );

    List<DepartmentHeatmapDto> toDtoList(
            List<EventRepository.DepartmentHeatmapRow> rows
    );
}