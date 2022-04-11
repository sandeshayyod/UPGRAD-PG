package com.upgrad.userservice.controller;

import com.upgrad.bmccommons.dto.UserDto;
import com.upgrad.userservice.controller.validator.UserValidator;
import com.upgrad.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(path = "users")
public class UserOnboardingController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> enroll(@Valid @RequestBody UserDto userDto) {
        UserValidator.validate(userDto);
        UserDto savedUserDto = userService.enroll(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{userID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable String userID) {
        UserDto savedUserDto = userService.getUser(userID);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    @PostMapping(path = "/{userId}/document", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam(value = "files") MultipartFile[] files, @PathVariable String userId) {
        final String responseString = "File(s) uploaded Successfully";
        userService.uploadDocuments(userId, files);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/documents/metadata", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getDocumentsMetadata(@PathVariable String userId) {
        List<String> metadata = userService.fetchUserDocuments(userId);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/documents/{documentName}", produces = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getDocumentsMetadata(@PathVariable String userId,
                                                              @PathVariable String documentName) {

        ByteArrayOutputStream downloadInputStream = userService.fetchDoctorDocument(userId, documentName);
        return ResponseEntity.ok()
                .contentType(contentType(documentName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userId + "\"")
                .body(downloadInputStream.toByteArray());
    }

    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
