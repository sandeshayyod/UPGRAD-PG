package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCategoryDao extends JpaRepository<VehicleCategory, Integer> {
    VehicleCategory findByVehicleCategoryId(int i);
}
