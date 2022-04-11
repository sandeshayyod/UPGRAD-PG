package com.upgrad.doctorservice.controller.advice;

import com.upgrad.bmccommons.dto.ErrorModel;
import com.upgrad.doctorservice.exception.DoctorDataAccessException;
import com.upgrad.doctorservice.exception.DoctorDataValidationException;
import com.upgrad.doctorservice.exception.DoctorServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DoctorDataAccessException.class)
    public ResponseEntity<ErrorModel> handleDataException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("BAD_REQUEST")
                .errorMessage("Resource not found")
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DoctorServiceException.class)
    public ResponseEntity<ErrorModel> handleServiceException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("NOT_WORKING")
                .errorMessage("Service not working. Please try after sometime")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DoctorDataValidationException.class)
    public ResponseEntity<ErrorModel> handleValidationException(DoctorDataValidationException e) {
        List<String> errorFields = e.getErrorFields();
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("ERR_INVALID_INPUT")
                .errorMessage("Invalid input. Parameter name: ")
                .errorFields(errorFields)
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
