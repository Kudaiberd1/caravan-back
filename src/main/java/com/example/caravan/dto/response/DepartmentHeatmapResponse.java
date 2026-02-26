package com.example.caravan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentHeatmapResponse {
    private Integer departmentId;
    private String departmentName;
    private Long employeesOnLocation;
    private Long employeesTotal;
}
