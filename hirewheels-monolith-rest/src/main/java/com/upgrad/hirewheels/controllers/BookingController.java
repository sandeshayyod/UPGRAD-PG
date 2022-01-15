package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/hirewheels/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/bookings")
    public ResponseEntity AddBooking(@RequestBody BookingDTO bookingDTO) throws Exception {

        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        settingVehicle(bookingDTO, booking);

        Booking savedBooking = bookingService.addBooking(booking);

        BookingDTO response = modelMapper.map(savedBooking, BookingDTO.class);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    private void settingVehicle(BookingDTO bookingDTO, Booking booking) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(bookingDTO.getVehicleId());
        booking.setVehicle(vehicle);
    }
}
