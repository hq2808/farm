package com.ride_sharing.ridesharing.service.impl;

import com.ride_sharing.ridesharing.dto.RideDTO;
import com.ride_sharing.ridesharing.model.Ride;
import com.ride_sharing.ridesharing.model.User;
import com.ride_sharing.ridesharing.repository.RideRepository;
import com.ride_sharing.ridesharing.repository.UserRepository;
import com.ride_sharing.ridesharing.service.RideService;
import com.ride_sharing.ridesharing.utils.RideStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public RideServiceImpl(RideRepository rideRepository, UserRepository userRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RideDTO createRide(RideDTO rideDTO) {
        User driver = userRepository.findById(driverId)
                .orElseThrow(() -> new UsernameNotFoundException("Driver not found"));

        Ride ride = new Ride();
        ride.setDriver(driver);
        ride.setStartLocation(rideDTO.getStartLocation());
        ride.setEndLocation(rideDTO.getEndLocation());
        ride.setDepartureTime(rideDTO.getDepartureTime());
        ride.setAvailableSeats(rideDTO.getAvailableSeats());
        ride.setStatus(RideStatus.OPEN);

        rideRepository.save(ride);
        rideDTO.setId(ride.getId());
        return rideDTO;
    }

    public List<RideDTO> getAllActiveRides() {
        return rideRepository.findByStatus(RideStatus.OPEN)
                .stream()
                .map(ride -> new RideDTO(
                        ride.getId(),
                        ride.getDriver().getId(),
                        ride.getStartLocation(),
                        ride.getEndLocation(),
                        ride.getDepartureTime(),
                        ride.getAvailableSeats(),
                        ride.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public RideDTO getRideById(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        return new RideDTO(ride.getId(), ride.getDriver().getId(),
                ride.getStartLocation(), ride.getEndLocation(),
                ride.getDepartureTime(), ride.getAvailableSeats(), ride.getStatus());
    }

    public void cancelRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setStatus(RideStatus.CANCELED);
        rideRepository.save(ride);
    }
}
