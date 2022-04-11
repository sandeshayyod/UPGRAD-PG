package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.model.PaymentException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class PaymentServiceImpl implements PaymentService {

    @Override
    public String makePayment(final double amount) throws PaymentException {
        if (amount <= 700) {
            return UUID.randomUUID().toString();
        }
        throw new PaymentException("Unable to process payment");
    }
}
