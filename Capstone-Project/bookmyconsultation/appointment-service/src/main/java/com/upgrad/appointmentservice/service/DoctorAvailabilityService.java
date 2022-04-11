package com.upgrad.appointmentservice.service;

import com.upgrad.appointmentservice.exception.AppointmentServiceException;
import com.upgrad.bmccommons.dto.DoctorAvailabilityDto;

public interface DoctorAvailabilityService {
    void saveDoctorAvailability(String doctorId, DoctorAvailabilityDto doctorAvailabilityDto) throws AppointmentServiceException;

    DoctorAvailabilityDto getDoctorAvailability(String doctorId);
}
