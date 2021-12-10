package com.upgrad.cc.booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingTransactionDto {
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;
}
