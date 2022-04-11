package com.upgrad.doctorservice.service.impl;

import com.upgrad.bmccommons.dto.ApproverDto;
import com.upgrad.bmccommons.dto.DoctorDto;
import com.upgrad.bmccommons.dto.Speciality;
import com.upgrad.bmccommons.dto.Status;
import com.upgrad.doctorservice.dao.DoctorDao;
import com.upgrad.doctorservice.dao.S3Repository;
import com.upgrad.doctorservice.entity.Doctor;
import com.upgrad.doctorservice.exception.DoctorDataAccessException;
import com.upgrad.doctorservice.exception.DoctorServiceException;
import com.upgrad.doctorservice.service.DoctorService;
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

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private S3Repository s3Repository;


    @Override
    public DoctorDto register(DoctorDto doctorDto) {
        log.info("Starting doctor registration for Doctor:{},{}", doctorDto.getLastName(), doctorDto.getFirstName());
        //TODO - implement below Steps
        //Step1: validation
        //taken care by javax validation

        //Step2: persist into mongoDB
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        doctor.setId(UUID.randomUUID().toString());
        doctor.setRegistrationDate(LocalDate.now());
        Doctor savedDoctor = doctorDao.save(doctor);
        log.info("Doctor persisted in DB with DoctorId:{}", savedDoctor.getId());

        //Step3: send doctor into kafka topic and send verification email
        log.info("Sending Doctor info into Kafka to send verification email to :{}", doctorDto.getEmailId());
        DoctorDto savedDoctorDto = modelMapper.map(savedDoctor, DoctorDto.class);
        producerService.publishDoctor(savedDoctorDto);
        return savedDoctorDto;
    }

    @Override
    public void uploadDocuments(String doctorId, MultipartFile[] files) {
        //TODO - implement service to upload the files to S3 bucket
        log.info("Uploading documents:[{}] for Doctor:{}", Arrays.stream(files).map(MultipartFile::getName).collect(Collectors.joining()), doctorId);
        //find doctor using dao
        uploadToS3Bucket(doctorId, files);
    }

    @Override
    public DoctorDto approve(String doctorId, ApproverDto approver) {
        //TODO - implement below Steps
        log.info("Attempting to approve DoctorId:{} by Approver:{}", doctorId, approver.getApprovedBy());
        Doctor savedDoctor = doctorDao.findById(doctorId)
                .orElseThrow(DoctorDataAccessException::new);

        if (savedDoctor.getStatus() != Status.PENDING) {
            log.error("Doctor with DoctorId:{} is already {}", doctorId, savedDoctor.getStatus());
            throw new DoctorServiceException();
        }
        savedDoctor.setStatus(Status.ACTIVE);
        savedDoctor.setApprovedBy(approver.getApprovedBy());
        savedDoctor.setApproverComments(approver.getApproverComments());
        savedDoctor.setVerificationDate(LocalDate.now());
        Doctor approvedDoctor = doctorDao.save(savedDoctor);
        log.info("Successfully approved DoctorId:{} by Approver:{}", doctorId, approver.getApprovedBy());
        //Step1: send doctor into kafka topic
        //Step2: send confirmation email
        DoctorDto approvedDoctorDto = modelMapper.map(approvedDoctor, DoctorDto.class);
        log.info("Sending Doctor info into Kafka to send Approval confirmation email to :{}", approvedDoctorDto.getEmailId());
        producerService.publishDoctor(approvedDoctorDto);
        return approvedDoctorDto;
    }

    @Override
    public DoctorDto reject(String doctorId, ApproverDto approver) {
        //TODO - implement below Steps
        log.info("Attempting to reject DoctorId:{} by Approver:{}", doctorId, approver.getApprovedBy());
        Doctor savedDoctor = doctorDao.findById(doctorId)
                .orElseThrow(DoctorDataAccessException::new);
        if (savedDoctor.getStatus() != Status.PENDING) {
            log.error("Doctor with DoctorId:{} is already {}", doctorId, savedDoctor.getStatus());
            throw new DoctorServiceException();
        }
        savedDoctor.setStatus(Status.REJECTED);
        savedDoctor.setApprovedBy(approver.getApprovedBy());
        savedDoctor.setApproverComments(approver.getApproverComments());
        savedDoctor.setVerificationDate(LocalDate.now());
        Doctor rejectedDoctor = doctorDao.save(savedDoctor);
        log.info("Successfully rejected DoctorId:{} by Approver:{}", doctorId, approver.getApprovedBy());

        //Step1: send doctor into kafka topic
        //producer.send(new ProducerRecord<String, Doctor>(Objects.requireNonNull(env.getProperty("doctor.register.topic.name")), "rejected", savedDoctor));
        //Step2: send confirmation email
        DoctorDto rejectedDoctorDto = modelMapper.map(rejectedDoctor, DoctorDto.class);
        log.info("Sending Doctor info into Kafka to send Rejected confirmation email to :{}", rejectedDoctorDto.getEmailId());
        producerService.publishDoctor(rejectedDoctorDto);
        return rejectedDoctorDto;
    }

    @Override
    public List<DoctorDto> fetch(String status, String speciality) {
        log.info("Fetching doctors with status:{} and speciality:{}", status, speciality);
        if (status == null && speciality == null) {
            return doctorDao.findAll().stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                    .collect(Collectors.toList());
        } else if (speciality == null) {
            return doctorDao.findByStatus(Status.valueOf(status)).stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                    .collect(Collectors.toList());
        } else if (status == null) {
            return doctorDao.findBySpeciality(Speciality.valueOf(speciality)).stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                    .collect(Collectors.toList());
        }
        return doctorDao.findByStatusAndSpeciality(Status.valueOf(status), Speciality.valueOf(speciality)).stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto fetchDoctorById(String doctorId) {
        log.info("Fetching doctor with DoctorId:{}", doctorId);
        Doctor savedDoctor = doctorDao.findById(doctorId).orElseThrow(DoctorDataAccessException::new);
        return modelMapper.map(savedDoctor, DoctorDto.class);
    }

    @Override
    public List<String> fetchDoctorDocuments(String doctorId) {
        log.info("Fetching documents associated DoctorId:{}", doctorId);
        return s3Repository.getFileNamesFromS3Bucket(doctorId);
    }

    @Override
    public ByteArrayOutputStream fetchDoctorDocument(String doctorId, String documentName) {
        return s3Repository.getUploadedFile(doctorId, documentName);
    }

    private void uploadToS3Bucket(String doctorId, MultipartFile[] files) {
        Arrays.stream(files).forEach(file -> {
            try {
                s3Repository.uploadFiles(doctorId, file);
            } catch (IOException e) {
                log.error("Cannot upload file:{} for Doctor:{}", file.getName(), doctorId);
                log.error("Exception occurred", e);
            }
        });
    }
}
