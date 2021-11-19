package com.example.aopdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {
    private String errorCode;
    private String errorMessage;
}
