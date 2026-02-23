package com.example.caravan.entity;

import com.example.caravan.entity.enums.ReportTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback_report")
public class FeedbackReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "send_date")
    private OffsetDateTime sendDate;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type")
    private ReportTypeEnum reportType;

    @Lob
    @Column(name = "body_text")
    private String bodyText;

    @Column(name = "subject", length = 255)
    private String subject;

    @Column(name = "deleted")
    private Boolean deleted;
}
