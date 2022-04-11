package com.upgrad.appointmentservice.service.impl;

import com.upgrad.appointmentservice.dao.AppointmentDao;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.entity.AppointmentStatus;
import com.upgrad.bmccommons.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentConsumerService {

    private final AppointmentDao appointmentDao;

    @KafkaListener(topics = "${bmc.payments.consumer.topic}", groupId = "${group.id}", containerFactory = "paymentKafkaListenerContainerFactory")
    public void listen(@Payload List<PaymentDto> paymentDtos) {
        paymentDtos.forEach(paymentDto -> log.info("Listening to  {}", paymentDto));
        for (PaymentDto paymentDto : paymentDtos) {
            String appointmentId = paymentDto.getAppointmentId();
            Optional<Appointment> appointmentOptional = appointmentDao.findByAppointmentId(appointmentId);
            if (appointmentOptional.isPresent()) {
                log.info("Updating the PaymentStatus to {} for appointmentId:{}", AppointmentStatus.CONFIRMED, appointmentId);
                Appointment oldAppointment = appointmentOptional.get();
                appointmentDao.deleteByAppointmentId(oldAppointment.getAppointmentId());
                Appointment newAppointment = createNewAppointment(oldAppointment);
                appointmentDao.save(newAppointment);
            } else {
                log.warn("Unable to find the appointment: {}", appointmentId);
            }
        }
    }

    private Appointment createNewAppointment(Appointment oldAppointment) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(oldAppointment.getAppointmentId());
        appointment.setDoctorId(oldAppointment.getDoctorId());
        appointment.setDoctorName(oldAppointment.getDoctorName());
        appointment.setUserName(oldAppointment.getUserName());
        appointment.setUserId(oldAppointment.getUserId());
        appointment.setUserEmailId(oldAppointment.getUserEmailId());
        appointment.setAppointmentDate(oldAppointment.getAppointmentDate());
        appointment.setTimeSlot(oldAppointment.getTimeSlot());
        appointment.setPaymentStatus(AppointmentStatus.CONFIRMED);
        appointment.setPriorMedicalHistory(oldAppointment.getPriorMedicalHistory());
        appointment.setCreatedDate(oldAppointment.getCreatedDate());
        appointment.setSymptoms(oldAppointment.getSymptoms());
        return appointment;
    }
}
