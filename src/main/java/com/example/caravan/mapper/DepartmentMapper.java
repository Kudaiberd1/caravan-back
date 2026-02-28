package com.example.caravan.mapper;

import com.example.caravan.dto.response.DepartmentResponse;
import com.example.caravan.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse toDto(
            Department row
    );

    List<DepartmentResponse> toDtoList(
            List<Department> rows
    );
}
