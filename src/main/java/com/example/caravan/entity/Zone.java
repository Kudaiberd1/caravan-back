package com.example.caravan.entity;

import com.example.caravan.entity.enums.AreaFunctionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Integer zoneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "zone_name")
    private String zoneName;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_function")
    private AreaFunctionEnum areaFunction;
}