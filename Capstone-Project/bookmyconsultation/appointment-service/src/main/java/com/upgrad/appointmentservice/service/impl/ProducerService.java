package com.upgrad.appointmentservice.service.impl;

import com.upgrad.bmccommons.dto.AppointmentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProducerService {
    private final KafkaTemplate<String, AppointmentDto> kafkaTemplate;

    @Value("${producer.topic}")
    private String producerTopic;

    public void publishAppointment(AppointmentDto appointmentDto) {
        log.info("Publishing " + appointmentDto);
        kafkaTemplate.send(producerTopic, appointmentDto);
    }
}
