package com.upgrad.doctorservice.dao;

import com.upgrad.bmccommons.dto.Speciality;
import com.upgrad.bmccommons.dto.Status;
import com.upgrad.doctorservice.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This layer will be used to interact with Database
 */
public interface DoctorDao extends MongoRepository<Doctor, String> {
    List<Doctor> findByStatus(Status status);

    List<Doctor> findBySpeciality(Speciality speciality);

    List<Doctor> findByStatusAndSpeciality(Status status, Speciality speciality);
}
