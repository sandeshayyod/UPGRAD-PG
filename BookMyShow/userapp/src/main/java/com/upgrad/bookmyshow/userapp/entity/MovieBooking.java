package com.upgrad.bookmyshow.userapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MovieBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movieBookingId;
}
