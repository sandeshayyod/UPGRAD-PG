package com.upgrad.vehicle.repository;

import com.upgrad.vehicle.entity.Vehicle;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
