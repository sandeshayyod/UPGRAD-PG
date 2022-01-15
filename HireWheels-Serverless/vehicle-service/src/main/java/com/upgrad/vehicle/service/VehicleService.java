package com.upgrad.vehicle.service;

import com.upgrad.vehicle.dto.VehicleDTO;
import com.upgrad.vehicle.entity.Vehicle;
import com.upgrad.vehicle.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        Iterable<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle: vehicles) {
            vehicleDTOList.add(modelMapper.map(vehicle, VehicleDTO.class));
        }
        return vehicleDTOList;
    }

    public VehicleDTO getVehicleById(String id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class)).orElse(null);
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle, VehicleDTO.class);
    }

    public void deleteById(String id) {
        vehicleRepository.deleteById(id);
    }
}
