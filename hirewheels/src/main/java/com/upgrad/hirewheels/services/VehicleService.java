package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.Vehicle;

import java.util.Set;

public interface VehicleService {

    Set<Vehicle> getAllVehicles();

    Set<Vehicle> getAvailableVehicles();
}
