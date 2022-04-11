package com.upgrad.appointmentservice.service;

import com.upgrad.bmccommons.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto bookAppointment(AppointmentDto appointmentDto);

    AppointmentDto getAppointment(String appointmentId);

    List<AppointmentDto> getUserAppointments(String userId);
}
