package com.upgrad.orderservice.rest;

import com.upgrad.commons.model.request.PaymentRequest;
import com.upgrad.commons.model.response.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public final class PaymentServiceClient {

    private static final String SERVICE_URL = "http://payment-service:8060/payments";

    private final RestTemplate restTemplate;

    public PaymentResponse makePayment(final PaymentRequest paymentRequest) {
        return restTemplate
                .postForObject(SERVICE_URL + "/make-payment", paymentRequest, PaymentResponse.class);
    }
}
