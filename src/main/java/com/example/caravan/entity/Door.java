package com.example.caravan.entity;

import com.example.caravan.entity.enums.AreaFunctionEnum;
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
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "door_name")
    private String doorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_function")
    private AreaFunctionEnum areaFunction;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    private Boolean deleted;

    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;
}