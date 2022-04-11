package com.upgrad.doctorservice.dao;

import com.upgrad.doctorservice.entity.DoctorRating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DoctorRatingDao extends MongoRepository<DoctorRating, String> {
    Optional<DoctorRating> findByDoctorId(String doctorId);
}
