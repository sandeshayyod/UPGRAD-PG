package com.upgrad.appointmentservice.service.impl;

import com.upgrad.appointmentservice.dao.AppointmentDao;
import com.upgrad.appointmentservice.dao.ConsultationSummaryDao;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.entity.AppointmentStatus;
import com.upgrad.appointmentservice.entity.ConsultationSummary;
import com.upgrad.appointmentservice.exception.AppointmentDataAccessException;
import com.upgrad.appointmentservice.exception.AppointmentServiceException;
import com.upgrad.appointmentservice.service.ConsultationService;
import com.upgrad.bmccommons.dto.ConsultationSummaryDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private ConsultationSummaryDao consultationSummaryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveConsultationSummary(ConsultationSummaryDto consultationSummaryDto) {
        log.info("Uploading consultation summary for Appointment:{}", consultationSummaryDto.getAppointmentId());
        Appointment appointment = appointmentDao.findByAppointmentId(consultationSummaryDto.getAppointmentId()).orElseThrow(AppointmentDataAccessException::new);
        if (appointment.getPaymentStatus() == AppointmentStatus.PENDING_PAYMENT) {
            log.error("Payment pending for {}", consultationSummaryDto.getAppointmentId());
            throw new AppointmentServiceException("Payment pending for " + consultationSummaryDto.getAppointmentId());
        }
        ConsultationSummary consultationSummary = modelMapper.map(consultationSummaryDto, ConsultationSummary.class);
        consultationSummary.setId(UUID.randomUUID().toString());
        consultationSummaryDao.save(consultationSummary);
        log.info("Successfully Uploaded consultation summary for Appointment:{}", consultationSummaryDto.getAppointmentId());

    }
}
