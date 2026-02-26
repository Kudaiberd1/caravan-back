package com.example.caravan.dto.request;

import com.example.caravan.entity.enums.ReportTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateRequest {
    private List<Integer> deleteRecipientIds;
    private String bodyText;
    private String subject;
}
