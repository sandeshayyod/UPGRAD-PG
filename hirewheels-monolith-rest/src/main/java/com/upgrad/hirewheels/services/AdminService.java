package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.Vehicle;

public interface AdminService {

    Vehicle registerVehicle(Vehicle vehicle);

    Vehicle changeAvailability(int vehicleId, int i);
}
