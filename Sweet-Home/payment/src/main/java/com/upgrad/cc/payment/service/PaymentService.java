package com.upgrad.cc.payment.service;

import com.upgrad.cc.payment.dto.BookingTransactionDto;
import com.upgrad.cc.payment.entity.TransactionDetailsEntity;

public interface PaymentService {

    int doPaymentTransaction(BookingTransactionDto bookingTransactionDto);

    TransactionDetailsEntity getTransactionDetail(int transactionId);
}
