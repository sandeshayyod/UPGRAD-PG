package com.upgrad.doctorservice.controller.validator;

import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.doctorservice.exception.DoctorDataValidationException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DoctorValidator {

    private static final String firstName = "First Name";
    private static final String lastName = "Last Name";
    private static final String mobile = "Mobile";
    private static final String dob = "Date Of Birth";
    private static final String emailId = "Email Id";
    private static final String pan = "PAN";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_MOBILE = Pattern.compile("[\\d]{10}");
    private static final Pattern VALID_DOB = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static void validate(DoctorDto doctor) {
        List<String> errorFields = new ArrayList<>(1);
        if (!StringUtils.hasLength(doctor.getFirstName()) || doctor.getFirstName().length() > 20) {
            errorFields.add(firstName);
        }
        if (!StringUtils.hasLength(doctor.getLastName()) || doctor.getLastName().length() > 20) {
            errorFields.add(lastName);
        }
        if (doctor.getPan() == null || doctor.getPan().length() != 10) {
            errorFields.add(pan);
        }
        if (doctor.getPan() == null || doctor.getPan().length() != 10) {
            errorFields.add(pan);
        }
        if (doctor.getEmailId() == null || !VALID_EMAIL_ADDRESS_REGEX.matcher(doctor.getEmailId()).matches()) {
            errorFields.add(emailId);
        }
        if (doctor.getMobile() == null || !VALID_MOBILE.matcher(doctor.getMobile()).matches()) {
            errorFields.add(mobile);
        }
        if (doctor.getDob() == null || !VALID_DOB.matcher(doctor.getDob()).matches()) {
            errorFields.add(dob);
        }
        if (!CollectionUtils.isEmpty(errorFields)) {
            throw new DoctorDataValidationException("Validation Failed", errorFields);
        }
    }
}
