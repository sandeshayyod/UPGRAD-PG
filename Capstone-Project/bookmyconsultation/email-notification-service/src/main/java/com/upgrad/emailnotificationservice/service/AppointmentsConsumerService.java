package com.upgrad.emailnotificationservice.service;

import com.upgrad.bmccommons.dto.AppointmentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppointmentsConsumerService {

    @Autowired
    private SesEmailService emailService;

    @KafkaListener(topics = "${bmc.appointments.consumer.topic}", groupId = "${group.id}", containerFactory = "appointmentKafkaListenerContainerFactory")
    public void listen(@Payload List<AppointmentDto> appointmentDtos) {
        appointmentDtos.forEach(appointmentDto -> log.info("Listening to  {}", appointmentDto));
        appointmentDtos.forEach(this::notifyUser);
    }

    private void notifyUser(AppointmentDto appointmentDto) {
        String userEmailId = appointmentDto.getUserEmailId();
        log.info("Sending Appointment Confirmed email to {}", appointmentDto.getUserEmailId());
        emailService.sendEmail(userEmailId, "Appointment Confirmed", "appointmentconfirmed.ftl");
    }
}
