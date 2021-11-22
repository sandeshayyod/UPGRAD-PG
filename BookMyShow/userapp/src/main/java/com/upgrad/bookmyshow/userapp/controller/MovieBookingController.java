package com.upgrad.bookmyshow.userapp.controller;

import com.upgrad.bookmyshow.userapp.service.MovieBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/bookmyshow-v1")
public class MovieBookingController {

    @Autowired
    private MovieBookingService bookingService;

    @GetMapping(value = "/check")
    public ResponseEntity<String> checkAvailability(@PathParam(value = "movieId") String movieId,
                                                    @PathParam(value = "theatreId") String theatreId,
                                                    @PathParam(value = "seats") String seats) {
        boolean isAvailable = bookingService.checkAvailable(Long.parseLong(movieId), Long.parseLong(theatreId), Long.parseLong(seats));
        String status = isAvailable ? "Available" : "Not Available";
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping(value = "/book", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> bookMovieBooking(@PathParam(value = "movieId") String movieId,
                                                   @PathParam(value = "theatreId") String theatreId,
                                                   @PathParam(value = "seats") String seats) {
        boolean isBooked = bookingService.bookMovie(Long.parseLong(movieId), Long.parseLong(theatreId), Long.parseLong(seats));
        String status = isBooked ? "Booked" : "Not Booked";
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping(value = "/cancel", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelMovieBooking(@PathParam(value = "movieId") String movieId,
                                                     @PathParam(value = "theatreId") String theatreId,
                                                     @PathParam(value = "seats") String seats) {
        boolean isCancelled = bookingService.cancelMovieBooking(Long.parseLong(movieId), Long.parseLong(theatreId), Long.parseLong(seats));
        String status = isCancelled ? "Cancelled" : "Not Cancelled";
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}
