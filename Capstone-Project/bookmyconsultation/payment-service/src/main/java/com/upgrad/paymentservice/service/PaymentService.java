package com.upgrad.paymentservice.service;

import com.upgrad.bmccommons.dto.PaymentDto;

public interface PaymentService {
    PaymentDto makePayment(String appointmentId);
}
