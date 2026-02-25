package com.example.caravan.mapper;

import com.example.caravan.dto.response.AnomalyResponse;
import com.example.caravan.repository.AnomalyRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnomalyMapper {
    AnomalyResponse toDto(
            AnomalyRepository.AnomalyDashboardRow row
    );

    List<AnomalyResponse> toDtoList(
            List<AnomalyRepository.AnomalyDashboardRow> rows
    );
}
