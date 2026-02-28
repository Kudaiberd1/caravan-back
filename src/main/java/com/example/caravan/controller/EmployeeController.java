package com.example.caravan.controller;

import com.example.caravan.dto.request.PaginationRequest;
import com.example.caravan.dto.response.EmployeeResponse;
import com.example.caravan.dto.response.PaginatedResponse;
import com.example.caravan.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<PaginatedResponse<EmployeeResponse>> getAllEmployees(@ParameterObject @ModelAttribute PaginationRequest paginationParams) {
       var problems  = employeeService.getAllEmployees(paginationParams);
         return ResponseEntity.ok(new PaginatedResponse<>(problems));
    }
}
