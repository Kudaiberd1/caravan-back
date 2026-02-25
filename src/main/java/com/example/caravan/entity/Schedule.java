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
    private Integer scheduleId;

    @Column(name = "schedule_name")
    private String scheduleName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    private Boolean deleted;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;
}