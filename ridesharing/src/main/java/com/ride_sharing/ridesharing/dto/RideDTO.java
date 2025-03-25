package com.ride_sharing.ridesharing.dto;

import com.ride_sharing.ridesharing.utils.RideStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RideDTO extends BaseDTO {
    private Long id;
    private Long driverId;
    private String startLocation;
    private String endLocation;
    private LocalDateTime departureTime;
    private Integer availableSeats;
    private RideStatus status;

    public RideDTO(Long id, Long driverId, String startLocation, String endLocation, LocalDateTime departureTime,
                   Integer availableSeats, RideStatus status) {
        this.id = id;
        this.driverId = driverId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.status = status;
    }
}
