package com.upgrad.ratingservice.service;

import com.upgrad.bmccommons.dto.DoctorRatingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProducerService {
    private final KafkaTemplate<String, DoctorRatingDto> kafkaTemplate;

    @Value("${producer.topic}")
    private String producerTopic;

    public void publishDoctorRating(DoctorRatingDto doctorRatingDto) {
        log.info("Publishing " + doctorRatingDto);
        kafkaTemplate.send(producerTopic, doctorRatingDto);
    }
}
