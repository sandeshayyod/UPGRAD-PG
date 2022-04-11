package com.upgrad.userservice.service;

import com.upgrad.bmccommons.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface UserService {

    UserDto enroll(UserDto userDto);

    UserDto getUser(String userID);

    void uploadDocuments(String userId, MultipartFile[] files);

    List<String> fetchUserDocuments(String userId);

    ByteArrayOutputStream fetchDoctorDocument(String userId, String documentName);
}
