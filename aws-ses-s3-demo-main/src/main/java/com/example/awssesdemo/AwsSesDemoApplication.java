package com.example.awssesdemo;

import com.amazonaws.services.s3.model.ObjectMetadata;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
public class AwsSesDemoApplication {

	private final SesEmailVerificationService verifyEmail;
	private final S3Repository s3Repository;

	public static void main(String[] args) {
		SpringApplication.run(AwsSesDemoApplication.class, args);
	}

	@GetMapping("/verify")
	public ResponseEntity verifyEmail(@RequestParam("email") String emailId){
		verifyEmail.verifyEmail(emailId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/email")
	public ResponseEntity sendEmail(@RequestBody User user) throws TemplateException, IOException, MessagingException {
		verifyEmail.sendEmail(user);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/users/{userId}/upload")
	public void uploadFiles(@PathVariable("userId") String userId, @RequestParam MultipartFile[] files) throws IOException {
		for(MultipartFile file: files) {
			s3Repository.uploadFiles(userId, file);
		}
	}



}
