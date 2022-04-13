package com.upgrad.cc.payment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {
    private String message;
    private int statusCode;
}