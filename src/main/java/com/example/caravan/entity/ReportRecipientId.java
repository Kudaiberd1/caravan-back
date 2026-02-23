package com.example.caravan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReportRecipientId implements Serializable {

    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "recipient_id")
    private Long recipientId;
}