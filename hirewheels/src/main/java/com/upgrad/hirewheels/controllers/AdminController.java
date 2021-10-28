package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/hirewheels/v1")
public class AdminController {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  AdminService adminService;

  @PostMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity AddVehicle(@RequestBody VehicleDTO vehicleDTO) throws Exception {
    Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);

    Vehicle savedVehicle = adminService.registerVehicle(vehicle);

    VehicleDTO response = modelMapper.map(savedVehicle, VehicleDTO.class);
    return new ResponseEntity(response, HttpStatus.CREATED);
  }

  @PutMapping(value = "/vehicles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable("id") int id) {

    Vehicle vehicle = adminService.changeAvailability(id, vehicleDTO.getAvailabilityStatus());
    VehicleDTO response = modelMapper.map(vehicle, VehicleDTO.class);
    return new ResponseEntity(response, HttpStatus.ACCEPTED);
  }
}
