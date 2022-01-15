package com.upgrad.vehicle.controller;

import com.upgrad.vehicle.dto.VehicleDTO;
import com.upgrad.vehicle.entity.Vehicle;
import com.upgrad.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(path = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicleDTOList = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }

    @GetMapping(path = "/vehicles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable String id) {
        VehicleDTO vehicleDTO = vehicleService.getVehicleById(id);
        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO savedVehicleDTO = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(savedVehicleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/vehicles/{id}")
    public ResponseEntity deleteVehicle(@PathVariable String id) {
        vehicleService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
