package com.ride_sharing.ridesharing.repository;

import com.ride_sharing.ridesharing.model.Ride;
import com.ride_sharing.ridesharing.utils.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByStatus(RideStatus status);
}
