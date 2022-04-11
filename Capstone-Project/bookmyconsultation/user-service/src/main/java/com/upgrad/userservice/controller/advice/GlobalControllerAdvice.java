package com.upgrad.userservice.controller.advice;

import com.upgrad.bmccommons.dto.ErrorModel;
import com.upgrad.userservice.exception.UserDataAccessException;
import com.upgrad.userservice.exception.UserDataValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserDataAccessException.class)
    public ResponseEntity<ErrorModel> handleDataAccessException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("ERR_RESOURCE_NOT_FOUND")
                .errorMessage("Requested resource is not available")
                .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserDataValidationException.class)
    public ResponseEntity<ErrorModel> handleServiceException(UserDataValidationException e) {
        List<String> errorFields = e.getErrorFields();
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("ERR_INVALID_INPUT")
                .errorMessage("Invalid input. Parameter name: ")
                .errorFields(errorFields)
                .build(), HttpStatus.BAD_REQUEST
        );
    }

}
