package com.upgrad.userservice.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class UserDataValidationException extends RuntimeException {
    private List<String> errorFields;

    public UserDataValidationException(String msg, List<String> errorFields) {
        super(msg);
        this.errorFields = errorFields;
    }
}
