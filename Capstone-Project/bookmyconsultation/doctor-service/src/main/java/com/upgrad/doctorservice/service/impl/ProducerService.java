package com.upgrad.doctorservice.service.impl;

import com.upgrad.bmccommons.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProducerService {
    private final KafkaTemplate<String, DoctorDto> kafkaTemplate;

    @Value("${producer.doctor.registration.topic}")
    private String producerTopic;

    public void publishDoctor(DoctorDto doctorDto) {
        log.info("Publishing {} to topic:{}", doctorDto, producerTopic);
        kafkaTemplate.send(producerTopic, doctorDto);
    }
}
