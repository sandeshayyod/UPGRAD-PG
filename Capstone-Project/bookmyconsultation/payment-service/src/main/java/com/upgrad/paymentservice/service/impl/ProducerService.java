package com.upgrad.paymentservice.service.impl;

import com.upgrad.bmccommons.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProducerService {
    private final KafkaTemplate<String, PaymentDto> kafkaTemplate;

    @Value("${producer.topic}")
    private String producerTopic;

    public void publishPayment(PaymentDto paymentDto) {
        log.info("Publishing " + paymentDto);
        kafkaTemplate.send(producerTopic, paymentDto);
    }
}
