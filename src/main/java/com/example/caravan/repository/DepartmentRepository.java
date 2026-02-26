package com.example.caravan.repository;

import com.example.caravan.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findDepartmentsByLocation_LocationName(String locationLocationName);
}
