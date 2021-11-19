package com.example.aopdemo.controller.advice;

import com.example.aopdemo.exception.ProductsDataAccessException;
import com.example.aopdemo.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ProductsDataAccessException.class)
    public ResponseEntity<ErrorModel> handleDataException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .errorCode("BAD_REQUEST")
                .errorMessage("Resource not found")
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
