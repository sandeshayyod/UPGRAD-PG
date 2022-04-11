package com.upgrad.userservice.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.upgrad.userservice.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Repository {

    private final AmazonS3 s3Client;
    @Autowired
    ObjectMetadata metadata;
    private String BUCKET_NAME = "bmc-sandesh";

    public void uploadFiles(String userId, MultipartFile file) throws IOException {
        String key = userId + "/" + file.getOriginalFilename();
        if (!s3Client.doesBucketExistV2(BUCKET_NAME)) {
            s3Client.createBucket(BUCKET_NAME);
        }
        s3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
    }

    public ByteArrayOutputStream getUploadedFile(String userId, String filename) {
        try {
            String key = userId + "/" + filename;
            S3Object s3object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, key));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return outputStream;
        } catch (IOException ioException) {
            log.error("IOException: " + ioException.getMessage());
        } catch (AmazonServiceException serviceException) {
            log.error("AmazonServiceException Message:    " + serviceException.getMessage());
            throw new UserServiceException();
        } catch (AmazonClientException clientException) {
            log.error("AmazonClientException Message: " + clientException.getMessage());
            throw new UserServiceException();
        }
        return null;
    }

    public List<String> getFileNamesFromS3Bucket(String userId) {
        if (!s3Client.doesBucketExistV2(BUCKET_NAME)) {
            return Collections.EMPTY_LIST;
        }
        ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest().withBucketName(BUCKET_NAME).withPrefix(userId));
        return objectListing.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

}
