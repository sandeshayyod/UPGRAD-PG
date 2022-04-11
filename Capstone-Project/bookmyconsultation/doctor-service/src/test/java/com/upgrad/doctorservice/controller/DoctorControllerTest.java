package com.upgrad.doctorservice.controller;

import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.bmccommons.dto.Speciality;
import com.upgrad.bmccommons.dto.Status;
import com.upgrad.doctorservice.service.DoctorService;
import com.upgrad.doctorservice.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoctorControllerTest {

    private final DoctorOnboardingController doctorController = new DoctorOnboardingController();
    private DoctorService doctorService;

    @BeforeAll
    public void init() {
        doctorService = Mockito.mock(DoctorServiceImpl.class);
    }

    @Test
    void test_register_when_doctor_passed_return_valid_response() {
        DoctorDto doctorDto = getValidDoctorDto();
        Mockito.when(doctorService.register(Mockito.any(DoctorDto.class)))
                .thenReturn(getPendingDoctor(doctorDto));
        ResponseEntity<DoctorDto> responseEntity = doctorController.register(doctorDto);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        DoctorDto pendingDoctor = responseEntity.getBody();
        Assertions.assertNotNull(pendingDoctor);
        Assertions.assertEquals(Status.PENDING, pendingDoctor.getStatus());
        Assertions.assertEquals("GENERATED DOCTOR ID", pendingDoctor.getId());
        Assertions.assertEquals(Speciality.DENTIST, pendingDoctor.getSpeciality());
        Assertions.assertEquals("2001-01-01", pendingDoctor.getRegistrationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void upload() {

    }

    @Test
    void approve() {
    }

    @Test
    void reject() {
    }

    @Test
    void fetch() {
    }

    @Test
    void testFetch() {
    }

    @Test
    void getDocumentsMetadata() {
    }

    @Test
    void testGetDocumentsMetadata() {
    }

    private DoctorDto getPendingDoctor(DoctorDto doctorDto) {
        doctorDto.setId("123");
        doctorDto.setRegistrationDate(LocalDate.of(2001, 01, 01));
        return doctorDto;
    }

    private DoctorDto getValidDoctorDto() {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setFirstName("Sandesh");
        doctorDto.setFirstName("Ayyod");
        doctorDto.setEmailId("sandesh.ayyod@gmail.com");
        doctorDto.setDob("1989-07-01");
        doctorDto.setPan("azipa1884b");
        doctorDto.setMobile("81505232606");
        doctorDto.setSpeciality(Speciality.DENTIST);
        return doctorDto;
    }
}