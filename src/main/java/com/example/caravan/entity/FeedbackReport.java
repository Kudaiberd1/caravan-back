package com.example.caravan.entity;

import com.example.caravan.entity.enums.ReportTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    private Integer reportId;

    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "send_date")
    private OffsetDateTime sendDate;

    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "report_type", columnDefinition = "report_type_enum")
    private ReportTypeEnum reportType;

    @Column(name = "body_text", columnDefinition = "text")
    private String bodyText;

    private String subject;

    private Boolean deleted;
}
