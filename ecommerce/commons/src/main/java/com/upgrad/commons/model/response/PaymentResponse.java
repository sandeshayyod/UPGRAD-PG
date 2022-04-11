package com.upgrad.commons.model.response;

import com.upgrad.commons.model.Error;
import com.upgrad.commons.model.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponse {

    private final String paymentId;
    private final Status paymentStatus;
    private final Error error;
}
