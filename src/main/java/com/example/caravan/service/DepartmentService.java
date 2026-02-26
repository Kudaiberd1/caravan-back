package com.example.caravan.service;


import com.example.caravan.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments(String location);
}
