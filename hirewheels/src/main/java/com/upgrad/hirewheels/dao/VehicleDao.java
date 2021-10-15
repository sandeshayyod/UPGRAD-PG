package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Integer> {

    Set<Vehicle> getAllByAvailabilityStatus(int availabilityStatus);
}
