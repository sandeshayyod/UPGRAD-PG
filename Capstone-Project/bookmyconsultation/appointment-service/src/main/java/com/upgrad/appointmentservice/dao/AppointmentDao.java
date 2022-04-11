package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentDao extends MongoRepository<Appointment, String> {
    List<Appointment> findAllByUserId(String userId);

    Optional<Appointment> findByAppointmentId(String appointmentId);

    void deleteByAppointmentId(String appointmentId);
}
