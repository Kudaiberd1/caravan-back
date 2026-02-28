package com.example.caravan.service.impl;

import com.example.caravan.dto.request.PaginationRequest;
import com.example.caravan.dto.response.EmployeeResponse;
import com.example.caravan.mapper.EmplyeeMapper;
import com.example.caravan.repository.EmployeeRepository;
import com.example.caravan.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmplyeeMapper employeeMapper;

    @Override
    public Page<EmployeeResponse> getAllEmployees(PaginationRequest paginationParams) {
        return employeeRepository.findAll(paginationParams.toPageable()).map(employeeMapper::toDto);
    }
}
