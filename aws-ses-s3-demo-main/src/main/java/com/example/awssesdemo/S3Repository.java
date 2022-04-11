package com.example.awssesdemo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class S3Repository {

    private AmazonS3 s3Client;
    private String BUCKET_NAME = "bmc.doctor.documents";// This needs to be a unique bucket name across all the regions.

    @Autowired
    ObjectMetadata metadata;

    @PostConstruct
    public void init(){
        String accessKey = "$$$$$$$$$$$$$$$$$$";
        String secretKey = "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        s3Client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1)
            .build();
    }

    public void uploadFiles(String userId, MultipartFile file) throws IOException {
        String key = userId + "/"+ file.getOriginalFilename();
        if(!s3Client.doesBucketExistV2(BUCKET_NAME)){
            s3Client.createBucket(BUCKET_NAME);
        }
        s3Client.putObject(BUCKET_NAME,key,file.getInputStream(),metadata);
    }



}
