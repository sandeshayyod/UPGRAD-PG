package com.upgrad.paymentservice.feign;

import com.upgrad.bmccommons.dto.AppointmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BMC-GATEWAY")
public interface AppointmentServiceFeignClient {

    @GetMapping(path = "${appointment.detail.mapping}")
    ResponseEntity<AppointmentDto> getAppointment(@PathVariable String appointmentId);
}
