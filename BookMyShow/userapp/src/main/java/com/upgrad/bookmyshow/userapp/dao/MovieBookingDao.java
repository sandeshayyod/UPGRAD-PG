package com.upgrad.bookmyshow.userapp.dao;

import com.upgrad.bookmyshow.userapp.entity.MovieBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieBookingDao extends JpaRepository<MovieBooking, Long> {
}
