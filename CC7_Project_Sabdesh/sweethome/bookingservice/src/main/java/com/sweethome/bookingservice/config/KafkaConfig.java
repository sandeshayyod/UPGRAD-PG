package com.sweethome.bookingservice.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfig {

	@Value("${kafka.bootstrap.server}")
	private String kafkaBootstrapServer;
	
	@Bean
	public  Producer<String, String> setUpKafkaPropoerties() {
		   Properties properties = new Properties();
		//Update the IP adress of Kafka server here//

			properties.put("bootstrap.servers", kafkaBootstrapServer);
	        properties.put("acks", "all");
	        properties.put("retries", 0);
	        properties.put("linger.ms", 0);
	        properties.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
	        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        properties.put("request.timeout.ms", 30000);
	        properties.put("timeout.ms", 30000);
	        properties.put("max.in.flight.requests.per.connection", 5);
	        properties.put("retry.backoff.ms", 5);

	        //Instantiate Producer Object
	        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
	        return producer;
	}

}
