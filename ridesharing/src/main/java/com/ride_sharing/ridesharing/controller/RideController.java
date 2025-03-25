package com.ride_sharing.ridesharing.controller;

import com.ride_sharing.ridesharing.dto.RideDTO;
import com.ride_sharing.ridesharing.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping
    public RideDTO createRide(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RideDTO rideDTO) {
        return rideService.createRide(Long.parseLong(userDetails.getUsername()), rideDTO);
    }

    @GetMapping
    public List<RideDTO> getAllActiveRides() {
        return rideService.getAllActiveRides();
    }

    @GetMapping("/{id}")
    public RideDTO getRideById(@PathVariable Long id) {
        return rideService.getRideById(id);
    }

    @DeleteMapping("/{id}")
    public void cancelRide(@PathVariable Long id) {
        rideService.cancelRide(id);
    }
}
