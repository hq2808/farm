package com.ride_sharing.ridesharing.model;

import com.ride_sharing.ridesharing.utils.RideStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ride extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private String startLocation;
    private String endLocation;
    private LocalDateTime departureTime;
    private Integer availableSeats;
    private Double price;

    @Enumerated(EnumType.STRING)
    private RideStatus status = RideStatus.OPEN;
}