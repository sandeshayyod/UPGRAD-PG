package com.upgrad.emailnotificationservice.service;

import com.upgrad.bmccommons.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DoctorConsumerService {

    private final SesEmailService emailService;

    @KafkaListener(topics = "${doctor.registration.consumer.topic}", groupId = "${group.id}", containerFactory = "doctorKafkaListenerContainerFactory")
    public void listen(@Payload List<DoctorDto> doctorDtos) {
        doctorDtos.forEach(doctorDto -> log.info("Listening to  {}", doctorDto));
        doctorDtos.forEach(doctorDto -> sendEmail(doctorDto));
    }

    private void sendEmail(DoctorDto doctorDto) {
        switch (doctorDto.getStatus()) {
            case PENDING:
                log.info("Sending verification email to {}", doctorDto.getEmailId());
                emailService.verifyEmail(doctorDto.getEmailId());
                break;
            case ACTIVE:
                log.info("Sending Approved email to {}", doctorDto.getEmailId());
                emailService.sendEmail(doctorDto.getEmailId(), "Registration Approved", "doctorapproved.ftl");
                break;
            case REJECTED:
                log.info("Sending Rejected email to {}", doctorDto.getEmailId());
                emailService.sendEmail(doctorDto.getEmailId(), "Registration Rejected", "doctorrejected.ftl");
                break;
        }
    }
}
