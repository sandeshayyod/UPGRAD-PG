package com.upgrad.paymentservice.controller;

import com.upgrad.bmccommons.dto.PaymentDto;
import com.upgrad.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(path = "/payments")
    public ResponseEntity<PaymentDto> makePayment(@RequestParam String appointmentId) {
        PaymentDto paymentDto = paymentService.makePayment(appointmentId);
        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

}
