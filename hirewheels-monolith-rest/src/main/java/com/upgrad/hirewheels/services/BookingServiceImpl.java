package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.BookingDao;
import com.upgrad.hirewheels.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingDao bookingDao;

    @Override
    public Booking addBooking(Booking booking) {
        if (booking == null) {
            return null;
        }
        return bookingDao.save(booking);
    }
}
