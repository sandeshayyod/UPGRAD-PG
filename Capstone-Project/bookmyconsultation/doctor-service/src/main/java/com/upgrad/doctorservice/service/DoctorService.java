package com.upgrad.doctorservice.service;

import com.upgrad.bmccommons.dto.ApproverDto;
import com.upgrad.bmccommons.dto.DoctorDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface DoctorService {

    DoctorDto register(DoctorDto doctor);

    void uploadDocuments(String doctorId, MultipartFile[] files);

    DoctorDto approve(String doctorId, ApproverDto approver);

    DoctorDto reject(String doctorId, ApproverDto approver);

    List<DoctorDto> fetch(String status, String speciality);

    DoctorDto fetchDoctorById(String doctorId);

    List<String> fetchDoctorDocuments(String doctorId);

    ByteArrayOutputStream fetchDoctorDocument(String doctorId, String documentName);
}

