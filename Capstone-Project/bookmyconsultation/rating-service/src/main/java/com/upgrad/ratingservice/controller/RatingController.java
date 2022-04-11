package com.upgrad.ratingservice.controller;

import com.upgrad.bmccommons.dto.DoctorRatingDto;
import com.upgrad.ratingservice.service.DoctorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RatingController {

    @Autowired
    private DoctorRatingService doctorRatingService;

    @PostMapping(path = "ratings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> submitRating(@RequestBody DoctorRatingDto doctorRatingDto) {
        doctorRatingService.submitRating(doctorRatingDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
