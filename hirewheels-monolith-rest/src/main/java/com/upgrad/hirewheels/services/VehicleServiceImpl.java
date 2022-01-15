package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public Set<Vehicle> getAllVehicles() {
        return new HashSet<>(Optional.of(vehicleDao.findAll()).orElse(new ArrayList<>(0)));
    }

    @Override
    public Set<Vehicle> getAvailableVehicles() {
        return vehicleDao.getAllByAvailabilityStatus(1);
    }
}
