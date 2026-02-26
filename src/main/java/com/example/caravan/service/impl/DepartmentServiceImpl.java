package com.example.caravan.service.impl;

import com.example.caravan.dto.response.DepartmentResponse;
import com.example.caravan.entity.Department;
import com.example.caravan.mapper.DepartmentMapper;
import com.example.caravan.repository.DepartmentRepository;
import com.example.caravan.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> getAllDepartments(String location) {
        List<Department> departments = departmentRepository.findDepartmentsByLocation_LocationName(location);
        return departmentMapper.toDtoList(departments);
    }
}
