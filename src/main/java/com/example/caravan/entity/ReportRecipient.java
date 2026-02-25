package com.example.caravan.entity;

import com.example.caravan.entity.enums.RecipientStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report_recipient")
@IdClass(ReportRecipientId.class)
public class ReportRecipient {

    @Id
    @ManyToOne
    @JoinColumn(name = "report_id")
    private FeedbackReport report;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Employee recipient;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private RecipientStatusEnum status;
}
