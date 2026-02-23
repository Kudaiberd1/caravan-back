package com.example.caravan.entity;

import com.example.caravan.entity.enums.EventDirectionEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId; // BIGSERIAL -> Long

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "event_time")
    private OffsetDateTime eventTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_direction")
    private EventDirectionEnum eventDirection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "door_id")
    private Door door;
}

