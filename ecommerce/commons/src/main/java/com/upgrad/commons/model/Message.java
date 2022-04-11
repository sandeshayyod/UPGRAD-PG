package com.upgrad.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {

    SUCCESS("Order Success", "Order placed successfully"),
    FAILURE("Order Failed", "Order placement failed");

    private final String subject;
    private final String body;
}
