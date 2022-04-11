package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.model.PaymentException;

public interface PaymentService {

    String makePayment(double amount) throws PaymentException;
}
