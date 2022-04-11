package com.upgrad.appointmentservice.service.impl;

import com.upgrad.appointmentservice.dao.DoctorAvailabilityDao;
import com.upgrad.appointmentservice.entity.DoctorAvailability;
import com.upgrad.appointmentservice.exception.AppointmentServiceException;
import com.upgrad.appointmentservice.exception.DoctorAvailabilityAccessException;
import com.upgrad.appointmentservice.feign.ServiceFeignClient;
import com.upgrad.appointmentservice.service.DoctorAvailabilityService;
import com.upgrad.bmccommons.dto.DoctorAvailabilityDto;
import com.upgrad.bmccommons.dto.DoctorDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {

    @Autowired
    private ServiceFeignClient serviceFeignClient;

    @Autowired
    private DoctorAvailabilityDao doctorAvailabilityDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveDoctorAvailability(String doctorId, DoctorAvailabilityDto doctorAvailabilityDto) throws AppointmentServiceException {
        log.info("saving available time slots for Doctor:{}", doctorId);
        final ResponseEntity<DoctorDto> responseEntity = serviceFeignClient.fetch(doctorId);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("Unable to connect to Doctor-Service");
            throw new AppointmentServiceException("Unable to connect to Doctor-Service");
        }
        Optional<DoctorAvailability> savedDoctorAvailability = doctorAvailabilityDao.findByDoctorId(doctorId);
        DoctorAvailability doctorAvailability;
        if (savedDoctorAvailability.isPresent()) {
            log.info("Doctor with DoctorId:{} already present. Updating Time slots", doctorId);
            doctorAvailability = savedDoctorAvailability.get();
            doctorAvailability.getAvailabilityMap().clear();
            doctorAvailability.setAvailabilityMap(doctorAvailabilityDto.getAvailabilityMap());
        } else {
            log.info("Doctor with DoctorId:{} uploaded with new Time slots", doctorId);
            doctorAvailability = modelMapper.map(doctorAvailabilityDto, DoctorAvailability.class);
            doctorAvailability.setId(UUID.randomUUID().toString());
        }
        doctorAvailabilityDao.save(doctorAvailability);
        log.info("Successfully updated available time slots for Doctor:{}", doctorId);
    }

    @Override
    public DoctorAvailabilityDto getDoctorAvailability(String doctorId) {
        log.info("getting available time slots for Doctor:{}", doctorId);
        DoctorAvailability doctorAvailability = doctorAvailabilityDao.findByDoctorId(doctorId).orElseThrow(DoctorAvailabilityAccessException::new);
        return modelMapper.map(doctorAvailability, DoctorAvailabilityDto.class);
    }
}
