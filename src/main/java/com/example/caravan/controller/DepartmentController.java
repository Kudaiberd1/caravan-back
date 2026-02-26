package com.example.caravan.controller;

import com.example.caravan.dto.response.DepartmentResponse;
import com.example.caravan.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(@RequestParam String location) {
        List<DepartmentResponse> departments = departmentService.getAllDepartments(location);
        return ResponseEntity.ok(departments);
    }
}
