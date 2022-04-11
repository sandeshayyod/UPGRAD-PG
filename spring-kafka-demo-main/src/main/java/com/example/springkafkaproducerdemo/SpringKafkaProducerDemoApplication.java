package com.example.springkafkaproducerdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
public class SpringKafkaProducerDemoApplication implements ApplicationRunner {

	private final ProducerService producerService;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaProducerDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Stream.of(User
			.builder()
			.userName("abcd")
			.age(25)
			.createDate(new Date())
			.build())
		.forEach(u->producerService.publishUser(u));
	}
}
