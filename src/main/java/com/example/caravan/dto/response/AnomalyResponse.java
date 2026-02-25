package com.example.caravan.dto.response;

import com.example.caravan.entity.enums.AnomalyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnomalyResponse {
    private Integer anomalyId;
    private AnomalyTypeEnum anomalyType;
    private String description;
    private String employeeName;
    private String departmentName;
    private String priorityLabel;
}
