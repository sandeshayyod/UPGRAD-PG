package com.upgrad.cc.booking.controller.advice;

import com.upgrad.cc.booking.exception.BookingInfoNotFoundException;
import com.upgrad.cc.booking.exception.BookingServiceException;
import com.upgrad.cc.booking.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BookingServiceException.class)
    public ResponseEntity<ErrorModel> handleServiceException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid mode of payment")
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BookingInfoNotFoundException.class)
    public ResponseEntity<ErrorModel> handleDataException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid Booking Id")
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorModel> handleRestClientException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid Transaction Id")
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
