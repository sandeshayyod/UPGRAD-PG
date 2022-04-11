package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.service.AppointmentService;
import com.upgrad.bmccommons.dto.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto savedAppointmentDto = appointmentService.bookAppointment(appointmentDto);
        return new ResponseEntity<>(savedAppointmentDto.getAppointmentId(), HttpStatus.OK);
    }

    @GetMapping(path = "/{appointmentId}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable String appointmentId) {
        AppointmentDto savedAppointmentDto = appointmentService.getAppointment(appointmentId);
        return new ResponseEntity<>(savedAppointmentDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/appointments")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@PathVariable String userId) {
        List<AppointmentDto> savedAppointmentDtos = appointmentService.getUserAppointments(userId);
        return new ResponseEntity<>(savedAppointmentDtos, HttpStatus.OK);
    }
}
