package com.upgrad.userservice.service.impl;

import com.upgrad.bmccommons.dto.UserDto;
import com.upgrad.userservice.dao.UserDao;
import com.upgrad.userservice.entity.User;
import com.upgrad.userservice.exception.UserDataAccessException;
import com.upgrad.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private S3Repository s3Repository;

    @Override
    public UserDto enroll(UserDto userDto) {
        log.info("Starting registration for User:{},{}", userDto.getLastName(), userDto.getFirstName());
        User user = modelMapper.map(userDto, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setCreatedDate(LocalDate.now());
        User savedUser = userDao.save(user);
        log.info("User persisted in DB with UserId:{}", savedUser.getId());
        log.info("Sending User info into Kafka to send verification email to :{}", user.getEmailId());
        userDto = modelMapper.map(savedUser, UserDto.class);
        producerService.publishUser(userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(String userID) {
        log.info("Fetching user with UserId:{}", userID);
        User savedUser = userDao.findById(userID).orElseThrow(() -> new UserDataAccessException("User " + userID + " not found"));
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void uploadDocuments(String userId, MultipartFile[] files) {
        userDao.findById(userId).orElseThrow(() -> new UserDataAccessException("User " + userId + " not found"));
        String filenames = Arrays.stream(files)
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.joining(","));
        log.info("Uploading files:[{}] of userId:{}", filenames, userId);
        Arrays.stream(files)
                .forEach(file -> {
                    try {
                        s3Repository.uploadFiles(userId, file);
                    } catch (IOException e) {
                        log.error("Cannot upload file:{} for User:{}", file.getName(), userId);
                        log.error("Exception occurred", e);
                    }
                });

        log.info("Files:[{}] uploaded successfully for userId:{}", filenames, userId);
    }

    @Override
    public List<String> fetchUserDocuments(String userId) {
        log.info("Fetching documents associated with userId={}", userId);
        return s3Repository.getFileNamesFromS3Bucket(userId);
    }

    @Override
    public ByteArrayOutputStream fetchDoctorDocument(String userId, String documentName) {
        log.info("Fetching document:{} associated with userId={}", documentName, userId);
        return s3Repository.getUploadedFile(userId, documentName);
    }
}
