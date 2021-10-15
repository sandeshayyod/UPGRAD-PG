package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<Booking, Integer> {
}
