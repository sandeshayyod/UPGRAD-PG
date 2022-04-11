package com.upgrad.doctorservice.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class DoctorDataValidationException extends RuntimeException {
    private List<String> errorFields;

    public DoctorDataValidationException(String msg, List<String> errorFields) {
        super(msg);
        this.errorFields = errorFields;
    }
}
