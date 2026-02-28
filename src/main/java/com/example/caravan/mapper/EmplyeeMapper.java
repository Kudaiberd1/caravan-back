package com.example.caravan.mapper;

import com.example.caravan.dto.response.EmployeeResponse;
import com.example.caravan.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmplyeeMapper {
    EmployeeResponse toDto(Employee employee);
}
