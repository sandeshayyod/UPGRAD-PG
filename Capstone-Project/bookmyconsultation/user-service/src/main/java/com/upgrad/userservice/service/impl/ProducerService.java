package com.upgrad.userservice.service.impl;

import com.upgrad.bmccommons.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProducerService {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    @Value("${producer.topic}")
    private String producerTopic;

    public void publishUser(UserDto userDto) {
        log.info("Publishing " + userDto);
        kafkaTemplate.send(producerTopic, userDto);
    }
}
