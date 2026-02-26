package com.example.caravan.dto.response;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Long id;
    private String reportType;
    private List<EmployeeResponse> recipients;
    private String subject;
    private String bodyText;
    private OffsetDateTime lastModifiedDate;
}
