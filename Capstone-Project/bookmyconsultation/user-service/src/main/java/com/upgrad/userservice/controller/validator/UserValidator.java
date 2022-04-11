package com.upgrad.userservice.controller.validator;

import com.upgrad.bmccommons.dto.UserDto;
import com.upgrad.userservice.exception.UserDataValidationException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String firstName = "First Name";
    private static final String lastName = "Last Name";
    private static final String mobile = "Mobile";
    private static final String dob = "Date Of Birth";
    private static final String emailId = "Email Id";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_MOBILE = Pattern.compile("[\\d]{10}");
    private static final Pattern VALID_DOB = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static void validate(UserDto user) throws UserDataValidationException {
        List<String> errorFields = new ArrayList<>(1);
        if (!StringUtils.hasLength(user.getFirstName()) || user.getFirstName().length() > 20) {
            errorFields.add(firstName);
        }
        if (!StringUtils.hasLength(user.getLastName()) || user.getLastName().length() > 20) {
            errorFields.add(lastName);
        }
        if (user.getEmailId() == null || !VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmailId()).matches()) {
            errorFields.add(emailId);
        }
        if (user.getMobile() == null || !VALID_MOBILE.matcher(user.getMobile()).matches()) {
            errorFields.add(mobile);
        }
        if (user.getDob() == null || !VALID_DOB.matcher(user.getDob()).matches()) {
            errorFields.add(dob);
        }
        if (!CollectionUtils.isEmpty(errorFields)) {
            throw new UserDataValidationException("Validation Failed", errorFields);
        }
    }
}
