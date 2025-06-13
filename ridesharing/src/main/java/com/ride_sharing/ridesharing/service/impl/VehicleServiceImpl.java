package com.ride_sharing.ridesharing.service.impl;

import com.ride_sharing.ridesharing.model.Vehicle;
import com.ride_sharing.ridesharing.repository.VehicleRepository;
import com.ride_sharing.ridesharing.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
