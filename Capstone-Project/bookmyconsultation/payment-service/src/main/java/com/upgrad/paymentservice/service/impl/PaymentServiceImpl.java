package com.upgrad.paymentservice.service.impl;

import com.upgrad.bmccommons.dto.AppointmentDto;
import com.upgrad.bmccommons.dto.PaymentDto;
import com.upgrad.paymentservice.dao.PaymentDao;
import com.upgrad.paymentservice.entity.Payment;
import com.upgrad.paymentservice.exception.PaymentServiceException;
import com.upgrad.paymentservice.feign.AppointmentServiceFeignClient;
import com.upgrad.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AppointmentServiceFeignClient appointmentServiceFeignClient;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private ProducerService producerService;

    @Override
    public PaymentDto makePayment(String appointmentId) {
        log.info("making payment for appointmentId: {}", appointmentId);
        ResponseEntity<AppointmentDto> appointmentDtoResponseEntity = appointmentServiceFeignClient.getAppointment(appointmentId);
        if (appointmentDtoResponseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("Unable to get the appointment-details");
            throw new PaymentServiceException("Unable to get the appointment-details");
        }
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setAppointmentId(appointmentId);
        payment.setCreateDate(LocalDateTime.now());
        payment = paymentDao.save(payment);

        PaymentDto paymentDto = mapToPaymentDto(payment);
        producerService.publishPayment(paymentDto);
        return paymentDto;
    }

    private PaymentDto mapToPaymentDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(payment.getId());
        paymentDto.setCreateDate(payment.getCreateDate());
        paymentDto.setAppointmentId(payment.getAppointmentId());
        return paymentDto;
    }
}
