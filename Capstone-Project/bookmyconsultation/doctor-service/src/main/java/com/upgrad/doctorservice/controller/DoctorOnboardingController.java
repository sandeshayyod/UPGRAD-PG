package com.upgrad.doctorservice.controller;

import com.upgrad.bmccommons.dto.ApproverDto;
import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.doctorservice.controller.validator.DoctorValidator;
import com.upgrad.doctorservice.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@RestController
@RequestMapping(path = "doctors")
public class DoctorOnboardingController {

    @Autowired
    private DoctorService doctorService;

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(path = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDto> register(@Valid @RequestBody DoctorDto doctor) {
        DoctorValidator.validate(doctor);
        log.info("Registering doctor={}", doctor);
        DoctorDto registeredDoctor = doctorService.register(doctor);
        return new ResponseEntity<>(registeredDoctor, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(path = "/{doctorId}/document", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam(value = "files") MultipartFile[] files, @PathVariable String doctorId) {
        log.info("Uploading files of size={}", files.length);
        final String responseString = "File(s) uploaded Successfully";
        doctorService.uploadDocuments(doctorId, files);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/{doctorId}/approve", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDto> approve(@RequestBody ApproverDto approver, @PathVariable String doctorId) {
        DoctorDto registeredDoctor = doctorService.approve(doctorId, approver);
        return new ResponseEntity<>(registeredDoctor, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/{doctorId}/reject", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDto> reject(@RequestBody ApproverDto approver, @PathVariable String doctorId) {
        DoctorDto registeredDoctor = doctorService.reject(doctorId, approver);
        return new ResponseEntity<>(registeredDoctor, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DoctorDto>> fetch(@RequestParam(name = "status", required = false) String status,
                                                 @RequestParam(name = "speciality", required = false) String speciality) {
        List<DoctorDto> doctors = doctorService.fetch(status, speciality);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/{doctorId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDto> fetch(@PathVariable String doctorId) {
        DoctorDto doctor = doctorService.fetchDoctorById(doctorId);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/{doctorId}/documents/metadata", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getDocumentsMetadata(@PathVariable String doctorId) {
        List<String> metadata = doctorService.fetchDoctorDocuments(doctorId);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/{doctorId}/documents/{documentName}", produces = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getDocument(@PathVariable String doctorId,
                                              @PathVariable String documentName) {
        ByteArrayOutputStream downloadInputStream = doctorService.fetchDoctorDocument(doctorId, documentName);
        return ResponseEntity.ok()
                .contentType(contentType(documentName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doctorId + "\"")
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
