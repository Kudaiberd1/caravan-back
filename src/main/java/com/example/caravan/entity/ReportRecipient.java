package com.example.caravan.entity;

import com.example.caravan.entity.enums.RecipientStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report_recipient")
public class ReportRecipient {

    @EmbeddedId
    private ReportRecipientId id;

    @MapsId("reportId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private FeedbackReport report;

    @MapsId("recipientId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Employee recipient;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RecipientStatusEnum status;
}
