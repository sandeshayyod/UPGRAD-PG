package com.upgrad.appointmentservice.feign;

import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.bmccommons.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "BMC-GATEWAY")
public interface ServiceFeignClient {

    @RequestMapping(value = "${doctorservice.fetchdoctor.mapping}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<DoctorDto> fetch(@PathVariable String doctorId);

    @RequestMapping(value = "${userservice.fetchdoctor.mapping}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(@PathVariable String userID);

}
