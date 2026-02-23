package com.example.caravan.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "planned_start_time")
    private OffsetDateTime plannedStartTime;

    @Column(name = "planned_end_time")
    private OffsetDateTime plannedEndTime;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "last_modified_by", length = 255)
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;
}