package com.upgrad.cc.booking.mapper;

import com.upgrad.cc.booking.dto.BookingInfoDto;
import com.upgrad.cc.booking.entity.BookingInfoEntity;
import com.upgrad.cc.booking.util.BookingUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingMapper {

    /**
     * method to convert dto into entity object
     *
     * @param bookingInfoDto - dto
     * @return BookingInfoEntity - entity
     */
    public static BookingInfoEntity fromBookingInfoDto(BookingInfoDto bookingInfoDto) {
        BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();
        bookingInfoEntity.setBookedOn(LocalDateTime.now());
        bookingInfoEntity.setAadharNumber(bookingInfoDto.getAadharNumber());
        bookingInfoEntity.setFromDate(BookingUtil.dateFromString(bookingInfoDto.getFromDate()));
        bookingInfoEntity.setToDate(BookingUtil.dateFromString(bookingInfoDto.getToDate()));
        bookingInfoEntity.setNumOfRooms(bookingInfoDto.getNumOfRooms());
        return bookingInfoEntity;
    }
}
