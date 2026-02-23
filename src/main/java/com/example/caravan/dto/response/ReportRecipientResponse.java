package com.example.caravan.dto.response;

import com.example.caravan.entity.enums.RecipientStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRecipientResponse {
    private Long employeeId;
    private String fullName;
    private RecipientStatusEnum status;
}
