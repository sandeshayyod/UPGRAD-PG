package com.upgrad.notificationservice;

import com.upgrad.bmccommons.dto.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

public class Consumer {

    public static void main(String[] args) {

        Properties props = new Properties();

        //Update the IP adress of Kafka server here//
        props.setProperty("bootstrap.servers", args[0]);
        props.setProperty("group.id", "bmc");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, DoctorDto> doctorConsumer = new KafkaConsumer<String, DoctorDto>(props);
        doctorConsumer.subscribe(Arrays.asList("doctor_registration"));
        //Prints the topic subscription list
        Set<String> doctorSubscribedTopics = doctorConsumer.subscription();
        for (String topic : doctorSubscribedTopics) {
            System.out.println(topic);
        }

        KafkaConsumer<String, UserDto> userConsumer = new KafkaConsumer<String, UserDto>(props);
        userConsumer.subscribe(Arrays.asList("user_registration"));
        //Prints the topic subscription list
        Set<String> userSubscribedTopics = userConsumer.subscription();
        for (String topic : userSubscribedTopics) {
            System.out.println(topic);
        }

        KafkaConsumer<String, AppointmentDto> appointmentConsumer = new KafkaConsumer<String, AppointmentDto>(props);
        appointmentConsumer.subscribe(Arrays.asList("bmc_appointments"));
        //Prints the topic subscription list
        Set<String> appointmentSubscribedTopics = appointmentConsumer.subscription();
        for (String topic : appointmentSubscribedTopics) {
            System.out.println(topic);
        }

        KafkaConsumer<String, PaymentDto> paymentsConsumer = new KafkaConsumer<String, PaymentDto>(props);
        paymentsConsumer.subscribe(Arrays.asList("bmc_payments"));
        //Prints the topic subscription list
        Set<String> paymentSubscribedTopics = paymentsConsumer.subscription();
        for (String topic : paymentSubscribedTopics) {
            System.out.println(topic);
        }

        KafkaConsumer<String, DoctorRatingDto> doctorRatingsConsumer = new KafkaConsumer<String, DoctorRatingDto>(props);
        doctorRatingsConsumer.subscribe(Arrays.asList("bmc_doctor_ratings"));
        //Prints the topic subscription list
        Set<String> doctorRatingsSubscribedTopics = doctorRatingsConsumer.subscription();
        for (String topic : doctorRatingsSubscribedTopics) {
            System.out.println(topic);
        }


        try {
            while (true) {
                ConsumerRecords<String, DoctorDto> doctorRecords = doctorConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, DoctorDto> record : doctorRecords) {
                    System.out.println(record.value());
                }
                ConsumerRecords<String, UserDto> userRecords = userConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, UserDto> record : userRecords) {
                    System.out.println(record.value());
                }
                ConsumerRecords<String, AppointmentDto> appointmentRecords = appointmentConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, AppointmentDto> record : appointmentRecords) {
                    System.out.println(record.value());
                }
                ConsumerRecords<String, PaymentDto> paymentRecords = paymentsConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, PaymentDto> record : paymentRecords) {
                    System.out.println(record.value());
                }
                ConsumerRecords<String, DoctorRatingDto> doctorRatingsRecords = doctorRatingsConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, DoctorRatingDto> record : doctorRatingsRecords) {
                    System.out.println(record.value());
                }
            }
        } finally {
            doctorConsumer.close();
            userConsumer.close();
            appointmentConsumer.close();
            paymentsConsumer.close();
            doctorRatingsConsumer.close();
        }
    }

}
