package com.upgrad.ratingservice.service;

import com.upgrad.bmccommons.dto.DoctorRatingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoctorRatingService {

    @Autowired
    private ProducerService producerService;

    public void submitRating(DoctorRatingDto doctorRatingDto) {
        log.info("Submitting rating: {} for doctorId: {}", doctorRatingDto.getRating(), doctorRatingDto.getDoctorId());
        producerService.publishDoctorRating(doctorRatingDto);
    }
}
