package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleDao.save(vehicle);
    }

    @Override
    public Vehicle changeAvailability(int vehicleId, int i) {
        Vehicle vehicle = vehicleDao.findById(vehicleId).orElse(null);
        if (vehicle == null) {
            logger.warn("No vehicle found with with vehicle id {}", vehicleId);
            return null;
        }
        vehicle.setAvailabilityStatus(i);
        return vehicleDao.save(vehicle);
    }
}
