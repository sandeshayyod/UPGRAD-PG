package com.upgrad.cc.payment.dao;

import com.upgrad.cc.payment.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<TransactionDetailsEntity, Integer> {
}
