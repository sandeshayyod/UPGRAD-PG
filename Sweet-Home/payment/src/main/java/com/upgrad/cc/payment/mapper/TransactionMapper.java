package com.upgrad.cc.payment.mapper;

import com.upgrad.cc.payment.dto.BookingTransactionDto;
import com.upgrad.cc.payment.entity.TransactionDetailsEntity;
import com.upgrad.cc.payment.util.PaymentMode;

public class TransactionMapper {

    /**
     * method to convert dto into entity object
     *
     * @param bookingTransactionDto - dto
     * @return TransactionDetailsEntity - entity
     */
    public static TransactionDetailsEntity fromTransactionDto(BookingTransactionDto bookingTransactionDto) {
        TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity();
        transactionDetailsEntity.setBookingId(bookingTransactionDto.getBookingId());
        transactionDetailsEntity.setCardNumber(bookingTransactionDto.getCardNumber());
        transactionDetailsEntity.setUpiId(bookingTransactionDto.getUpiId());
        transactionDetailsEntity.setPaymentMode(PaymentMode.valueOf(bookingTransactionDto.getPaymentMode()));
        return transactionDetailsEntity;
    }
}
