package com.upgrad.cc.booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingInfoDto {
    private String fromDate;
    private String toDate;
    private String aadharNumber;
    private int numOfRooms;
}
