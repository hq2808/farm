package com.ride_sharing.ridesharing.service;

import com.ride_sharing.ridesharing.dto.RideDTO;

import java.util.List;

public interface RideService {
    RideDTO createRide(RideDTO rideDTO);
    List<RideDTO> getAllActiveRides();
    RideDTO getRideById(Long rideId);
    void cancelRide(Long rideId);

}
