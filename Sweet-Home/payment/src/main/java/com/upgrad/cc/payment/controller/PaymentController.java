package com.upgrad.cc.payment.controller;

import com.upgrad.cc.payment.dto.BookingTransactionDto;
import com.upgrad.cc.payment.entity.TransactionDetailsEntity;
import com.upgrad.cc.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/transaction")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(@Autowired PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> doPaymentTransaction(@RequestBody BookingTransactionDto bookingTransactionDto) {
        log.info("called doPaymentTransaction with {}", bookingTransactionDto);
        //call service class to perform business logic
        int transactionId = paymentService.doPaymentTransaction(bookingTransactionDto);
        log.info("completed doBooking with transactionId {}", transactionId);
        return new ResponseEntity<>(transactionId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{transactionId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetail(@PathVariable int transactionId) {
        log.info("called doPaymentTransaction with transactionId:{}", transactionId);
        //call service class to perform business logic
        TransactionDetailsEntity transactionDetailsEntity = paymentService.getTransactionDetail(transactionId);
        log.info("completed doBooking with transactionDetailsEntity {}", transactionDetailsEntity);
        return new ResponseEntity<>(transactionDetailsEntity, HttpStatus.OK);
    }

}
