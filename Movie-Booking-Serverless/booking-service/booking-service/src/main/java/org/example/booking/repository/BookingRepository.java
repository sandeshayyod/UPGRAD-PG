package org.example.booking.repository;

import org.example.booking.entity.Booking;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface BookingRepository extends CrudRepository<Booking, String> {
}
