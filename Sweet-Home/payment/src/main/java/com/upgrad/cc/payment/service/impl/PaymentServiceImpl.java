package com.upgrad.cc.payment.service.impl;

import com.upgrad.cc.payment.dao.TransactionDao;
import com.upgrad.cc.payment.dto.BookingTransactionDto;
import com.upgrad.cc.payment.entity.TransactionDetailsEntity;
import com.upgrad.cc.payment.exception.TransactionNotFoundException;
import com.upgrad.cc.payment.mapper.TransactionMapper;
import com.upgrad.cc.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final TransactionDao transactionDao;

    public PaymentServiceImpl(@Autowired TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public int doPaymentTransaction(BookingTransactionDto bookingTransactionDto) {
        //invoke dao class method to perform CRUD calls
        TransactionDetailsEntity transactionDetailsEntity = TransactionMapper.fromTransactionDto(bookingTransactionDto);
        transactionDetailsEntity = transactionDao.save(transactionDetailsEntity);
        return transactionDetailsEntity.getTransactionId();
    }

    @Override
    public TransactionDetailsEntity getTransactionDetail(int transactionId) {
        //invoke dao class method to fetch transaction info based on ID
        return transactionDao.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with ID:" + transactionId + " not found."));
    }
}
