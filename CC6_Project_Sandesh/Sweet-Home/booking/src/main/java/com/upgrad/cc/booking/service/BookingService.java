package com.upgrad.cc.booking.service;

import com.upgrad.cc.booking.dto.BookingInfoDto;
import com.upgrad.cc.booking.dto.BookingTransactionDto;
import com.upgrad.cc.booking.entity.BookingInfoEntity;

public interface BookingService {
    BookingInfoEntity doBooking(BookingInfoDto bookingInfoDto);

    BookingInfoEntity doBookingTransaction(int bookingId, BookingTransactionDto bookingTransactionDto);
}
