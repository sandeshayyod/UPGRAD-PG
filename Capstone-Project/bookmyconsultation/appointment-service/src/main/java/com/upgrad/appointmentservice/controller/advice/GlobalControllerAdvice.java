package com.upgrad.appointmentservice.controller.advice;

import com.upgrad.appointmentservice.exception.AppointmentDataAccessException;
import com.upgrad.appointmentservice.exception.AppointmentServiceException;
import com.upgrad.appointmentservice.exception.DoctorAvailabilityAccessException;
import com.upgrad.bmccommons.dto.ErrorModel;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({AppointmentDataAccessException.class, DoctorAvailabilityAccessException.class})
    public ResponseEntity<ErrorModel> handleDataException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("BAD_REQUEST")
                .errorMessage("Resource not found")
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AppointmentServiceException.class)
    public ResponseEntity<ErrorModel> handleServiceException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("NOT_WORKING")
                .errorMessage("Service not working. Please try after sometime")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleServiceException(MethodArgumentNotValidException e) {
        List<String> errorFields = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("ERR_INVALID_INPUT")
                .errorMessage("Invalid input. Parameter name: ")
                .errorFields(errorFields)
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
