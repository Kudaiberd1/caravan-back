package com.example.caravan.entity;

import com.example.caravan.entity.enums.AreaFunctionEnum;
import com.example.caravan.entity.enums.DoorDirectionEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "door")
public class Door {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "door_id")
    private Integer doorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Column(name = "door_name")
    private String doorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "door_direction")
    private DoorDirectionEnum doorDirection;
}