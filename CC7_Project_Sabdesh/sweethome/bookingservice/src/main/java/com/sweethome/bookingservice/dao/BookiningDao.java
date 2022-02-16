package com.sweethome.bookingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweethome.bookingservice.model.entity.BookingInfoEntity;

@Repository
public interface BookiningDao extends JpaRepository<BookingInfoEntity, Integer>{

}
