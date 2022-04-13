package com.upgrad.cc.booking.dao;

import com.upgrad.cc.booking.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingInfoDao extends JpaRepository<BookingInfoEntity, Integer> {
}
