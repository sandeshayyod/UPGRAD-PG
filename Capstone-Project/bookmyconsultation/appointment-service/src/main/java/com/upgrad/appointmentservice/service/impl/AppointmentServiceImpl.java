package com.upgrad.appointmentservice.service.impl;

import com.upgrad.appointmentservice.dao.AppointmentDao;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.entity.AppointmentStatus;
import com.upgrad.appointmentservice.exception.AppointmentDataAccessException;
import com.upgrad.appointmentservice.exception.AppointmentServiceException;
import com.upgrad.appointmentservice.feign.ServiceFeignClient;
import com.upgrad.appointmentservice.service.AppointmentService;
import com.upgrad.bmccommons.dto.AppointmentDto;
import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.bmccommons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private ServiceFeignClient serviceFeignClient;


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private ProducerService producerService;

    @Override
    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) {
        log.info("Booking appointment for User:{} with Doctor:{} for timeSlot:{}", appointmentDto.getUserId(), appointmentDto.getDoctorId(), appointmentDto.getTimeSlot());
        final ResponseEntity<DoctorDto> doctorDtoResponseEntity = serviceFeignClient.fetch(appointmentDto.getDoctorId());
        final ResponseEntity<UserDto> userDtoResponseEntity = serviceFeignClient.getUser(appointmentDto.getUserId());
        if (doctorDtoResponseEntity.getStatusCode() != HttpStatus.OK || userDtoResponseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("Unable to connect to Doctor-Service OR User-Service");
            throw new AppointmentServiceException("Unable to connect to Doctor-Service OR User-Service");
        }
        Appointment appointment = mapper.map(appointmentDto, Appointment.class);
        appointment.setAppointmentId(UUID.randomUUID().toString());
        appointment.setPaymentStatus(AppointmentStatus.PENDING_PAYMENT);
        appointment.setUserEmailId(Objects.requireNonNull(userDtoResponseEntity.getBody()).getEmailId());
        appointmentDao.save(appointment);
        log.info("Successfully Booking appointment:{} for User:{} with Doctor:{} for timeSlot:{}", appointment.getAppointmentId(),
                appointmentDto.getUserId(), appointmentDto.getDoctorId(), appointmentDto.getTimeSlot());
        appointmentDto = mapper.map(appointment, AppointmentDto.class);
        producerService.publishAppointment(appointmentDto);
        return appointmentDto;
    }

    @Override
    public AppointmentDto getAppointment(String appointmentId) {
        log.info("Getting appointment details for appointmentId:{}", appointmentId);
        Appointment appointment = appointmentDao.findByAppointmentId(appointmentId).orElseThrow(AppointmentDataAccessException::new);
        return mapper.map(appointment, AppointmentDto.class);
    }

    @Override
    public List<AppointmentDto> getUserAppointments(String userId) {
        log.info("Getting appointment details for userId:{}", userId);
        List<Appointment> appointments = appointmentDao.findAllByUserId(userId);
        return appointments.stream().map(appointment -> mapper.map(appointment, AppointmentDto.class)).collect(Collectors.toList());
    }
}
