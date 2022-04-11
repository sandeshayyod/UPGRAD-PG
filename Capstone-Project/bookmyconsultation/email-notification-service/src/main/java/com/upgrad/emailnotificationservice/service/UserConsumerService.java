package com.upgrad.emailnotificationservice.service;

import com.upgrad.bmccommons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserConsumerService {

    @Autowired
    private SesEmailService emailService;

    @KafkaListener(topics = "${user.registration.consumer.topic}", groupId = "${group.id}", containerFactory = "userKafkaListenerContainerFactory")
    public void listen(@Payload List<UserDto> userDtos) {
        userDtos.forEach(userDto -> log.info("Listening to  {}", userDto));
        userDtos.forEach(userDto -> {
            log.info("Sending Welcome User email to {}", userDto.getEmailId());
            emailService.sendEmail(userDto.getEmailId(), "Welcome User", "userwelcome.ftl");
        });
    }
}
