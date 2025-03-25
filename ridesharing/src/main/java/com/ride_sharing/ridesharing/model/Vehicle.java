package com.ride_sharing.ridesharing.model;

import com.ride_sharing.ridesharing.utils.VehicleStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;  // Mỗi xe thuộc về một tài xế

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;  // Biển số xe

    @Column(name = "model", nullable = false)
    private String model;  // Model xe

    @Column(name = "seats", nullable = false)
    private Integer seats;  // Số chỗ ngồi

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status = VehicleStatus.AVAILABLE;
}