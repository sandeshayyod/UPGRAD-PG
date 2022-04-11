package com.upgrad.emailnotificationservice.controller.advice;

import com.upgrad.bmccommons.dto.ErrorModel;
import com.upgrad.emailnotificationservice.exception.EmailNotificationServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(EmailNotificationServiceException.class)
    public ResponseEntity<ErrorModel> handleServiceException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("NOT_WORKING")
                .errorMessage("Service not working. Please try after sometime")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
