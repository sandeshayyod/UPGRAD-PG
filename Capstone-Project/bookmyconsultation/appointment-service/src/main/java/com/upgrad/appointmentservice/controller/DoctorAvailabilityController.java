package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.service.DoctorAvailabilityService;
import com.upgrad.bmccommons.dto.DoctorAvailabilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "doctor")
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService doctorAvailabilityService;

    @PostMapping(path = "/{doctorId}/availability")
    public ResponseEntity<String> saveDoctorAvailability(@PathVariable String doctorId,
                                                         @RequestBody DoctorAvailabilityDto doctorAvailabilityDto) {
        doctorAvailabilityService.saveDoctorAvailability(doctorId, doctorAvailabilityDto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(path = "/{doctorId}/availability")
    public ResponseEntity<DoctorAvailabilityDto> getDoctorAvailability(@PathVariable String doctorId) {
        DoctorAvailabilityDto doctorAvailabilityDto = doctorAvailabilityService.getDoctorAvailability(doctorId);
        return new ResponseEntity<>(doctorAvailabilityDto, HttpStatus.OK);
    }
}
