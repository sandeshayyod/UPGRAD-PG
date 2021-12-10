package com.upgrad.cc.payment.entity;

import com.upgrad.cc.payment.util.PaymentMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "transaction")
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "payment_mode", length = 10)
    private PaymentMode paymentMode;

    @Column(name = "booking_id", nullable = false, unique = true)
    private int bookingId;

    @Column(name = "upi_id", length = 50)
    private String upiId;

    @Column(name = "card_number", length = 50)
    private String cardNumber;
}
