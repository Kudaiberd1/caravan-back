package com.example.caravan.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRecipientId implements Serializable {
    private FeedbackReport report;
    private Employee recipient;
}