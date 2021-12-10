package com.upgrad.cc.booking.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "booking")
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private int id;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Column(name = "aadhar_number")
    private String aadharNumber;

    @Column(name = "no_of_rooms")
    private int numOfRooms;

    @Column(name = "room_numbers")
    private String roomNumbers;

    @Column(name = "room_price", nullable = false)
    private int roomPrice = 0;

    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "booked_on")
    private LocalDateTime bookedOn;

}
