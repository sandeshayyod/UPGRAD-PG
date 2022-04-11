package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.entity.ConsultationSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsultationSummaryDao extends MongoRepository<ConsultationSummary, String> {
}
