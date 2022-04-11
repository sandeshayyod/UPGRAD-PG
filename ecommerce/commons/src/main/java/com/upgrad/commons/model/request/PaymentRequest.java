package com.upgrad.commons.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequest {

    private final String userId;
    private final double amount;
}

