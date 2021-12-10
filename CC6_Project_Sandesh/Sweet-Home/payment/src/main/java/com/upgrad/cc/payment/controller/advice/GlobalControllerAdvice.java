package com.upgrad.cc.payment.controller.advice;

import com.upgrad.cc.payment.exception.TransactionNotFoundException;
import com.upgrad.cc.payment.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorModel> handleDataException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid Transaction Id")
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorModel> handleSQLException() {
        return new ResponseEntity<>(ErrorModel.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid Transaction Id")
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
