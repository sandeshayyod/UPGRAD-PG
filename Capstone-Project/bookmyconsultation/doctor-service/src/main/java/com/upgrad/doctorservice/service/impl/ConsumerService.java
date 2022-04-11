package com.upgrad.doctorservice.service.impl;

import com.upgrad.bmccommons.dto.DoctorRatingDto;
import com.upgrad.doctorservice.dao.DoctorRatingDao;
import com.upgrad.doctorservice.entity.DoctorRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ConsumerService {

    @Autowired
    private DoctorRatingDao doctorRatingDao;

    @KafkaListener(topics = "${consumer.doctor.rating.topic}", groupId = "${group.id}", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listen(@Payload List<DoctorRatingDto> doctorRatingDtos) {
        doctorRatingDtos.forEach(this::updateAverageDoctorRating);
    }

    private void updateAverageDoctorRating(DoctorRatingDto doctorRatingDto) {
        log.info("Received Doctor rating from Kafka for {}", doctorRatingDto);
        Optional<DoctorRating> doctorRatingOptional = doctorRatingDao.findByDoctorId(doctorRatingDto.getDoctorId());
        if (doctorRatingOptional.isPresent()) {
            DoctorRating doctorRating = doctorRatingOptional.get();
            int oldRating = doctorRatingOptional.get().getRating();
            int newRating = doctorRatingDto.getRating();
            int avgRating = (oldRating + newRating) / 2;
            doctorRating.setRating(avgRating);
            doctorRatingDao.save(doctorRating);
        } else {
            DoctorRating doctorRating = new DoctorRating();
            doctorRating.setDoctorId(doctorRatingDto.getDoctorId());
            doctorRating.setRating(doctorRatingDto.getRating());
            doctorRatingDao.save(doctorRating);
        }

        log.info("Successfully updated doctor rating for DoctorId: {}", doctorRatingDto.getDoctorId());
    }
}
