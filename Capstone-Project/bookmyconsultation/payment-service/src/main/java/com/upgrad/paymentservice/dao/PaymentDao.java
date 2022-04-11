package com.upgrad.paymentservice.dao;

import com.upgrad.paymentservice.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentDao extends MongoRepository<Payment, String> {
}
