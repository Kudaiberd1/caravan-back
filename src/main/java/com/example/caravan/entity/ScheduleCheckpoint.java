package com.example.caravan.entity;

import com.example.caravan.entity.enums.AreaFunctionEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule_checkpoints")
public class ScheduleCheckpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkpoint_id")
    private Integer checkpointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    @Column(name = "required_area")
    private AreaFunctionEnum requiredArea;

    private LocalTime startTime;
    private LocalTime endTime;
}
