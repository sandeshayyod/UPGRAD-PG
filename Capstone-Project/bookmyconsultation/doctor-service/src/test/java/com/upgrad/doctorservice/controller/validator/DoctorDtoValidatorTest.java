package com.upgrad.doctorservice.controller.validator;

import com.upgrad.bmccommons.dto.DoctorDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DoctorDtoValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void test_DoctorDto_validation() {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setFirstName("Sandesh AYYid aAUKFPAFFFAJNDFK");
        doctorDto.setFirstName("Ayyod JAUJFNAKLKANFJFHALFF");
        doctorDto.setEmailId("sandesh.ayyodgmail.com");
        doctorDto.setDob("01-07-199");
        doctorDto.setPan("azipa184b");
        doctorDto.setMobile("815052606");
        Set<ConstraintViolation<DoctorDto>> violations = validator.validate(doctorDto);
        assertEquals(5, violations.size());
        assertFalse(violations.isEmpty());
    }
}
