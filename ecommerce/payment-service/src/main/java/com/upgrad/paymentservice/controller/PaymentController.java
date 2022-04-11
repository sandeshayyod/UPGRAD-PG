package com.upgrad.paymentservice.controller;

import com.upgrad.commons.model.Error;
import com.upgrad.commons.model.Status;
import com.upgrad.commons.model.request.PaymentRequest;
import com.upgrad.commons.model.response.PaymentResponse;
import com.upgrad.paymentservice.model.PaymentException;
import com.upgrad.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public final class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/make-payment")
    public PaymentResponse makePayment(@RequestBody final PaymentRequest paymentRequest) {
        try {
            log.info("making payment for user={} and amount={}", paymentRequest.getUserId(), paymentRequest.getAmount());
            return PaymentResponse.builder()
                    .paymentId(paymentService.makePayment(paymentRequest.getAmount()))
                    .paymentStatus(Status.SUCCESSFUL)
                    .build();
        } catch (final PaymentException e) {
            log.info("exception while making payment for user={} and amount={}",
                    paymentRequest.getUserId(), paymentRequest.getAmount(), e);
            return PaymentResponse.builder()
                    .paymentStatus(Status.FAILED)
                    .error(Error.builder().message(e.getMessage()).build())
                    .build();
        }
    }
}