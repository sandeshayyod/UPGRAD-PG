package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.entity.DoctorAvailability;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DoctorAvailabilityDao extends MongoRepository<DoctorAvailability, String> {
    Optional<DoctorAvailability> findByDoctorId(String doctorId);
}
