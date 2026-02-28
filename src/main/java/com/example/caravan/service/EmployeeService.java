package com.example.caravan.service;

import com.example.caravan.dto.request.PaginationRequest;
import com.example.caravan.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<EmployeeResponse> getAllEmployees(PaginationRequest paginationParams);
}
