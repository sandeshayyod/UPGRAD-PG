package com.upgrad.cc.booking.service.impl;

import com.upgrad.cc.booking.dao.BookingInfoDao;
import com.upgrad.cc.booking.dto.BookingInfoDto;
import com.upgrad.cc.booking.dto.BookingTransactionDto;
import com.upgrad.cc.booking.entity.BookingInfoEntity;
import com.upgrad.cc.booking.exception.BookingInfoNotFoundException;
import com.upgrad.cc.booking.exception.BookingServiceException;
import com.upgrad.cc.booking.mapper.BookingMapper;
import com.upgrad.cc.booking.service.BookingService;
import com.upgrad.cc.booking.util.BookingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.upgrad.cc.booking.util.BookingConstants.*;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    public static final int PRICE_PER_ROOM = 1000;
    private final BookingInfoDao bookingInfoDao;
    private final RestTemplate restTemplate;
    private final Producer<String, String> producer;

    @Value("${kafka.notification.topic}")
    private String topic;

    @Value("${paymentservice.transaction.url}")
    private String transactionUrl;

    public BookingServiceImpl(@Autowired BookingInfoDao bookingInfoDao,
                              @Autowired RestTemplate restTemplate,
                              @Autowired Producer<String, String> producer) {
        this.bookingInfoDao = bookingInfoDao;
        this.restTemplate = restTemplate;
        this.producer = producer;
    }

    @Override
    public BookingInfoEntity doBooking(BookingInfoDto bookingInfoDto) {
        try {
            //map dto into entity using mapper util method
            BookingInfoEntity bookingInfoEntity = BookingMapper.fromBookingInfoDto(bookingInfoDto);

            //business logic to get the available rooms
            List<String> availableRooms = BookingUtil.getRandomNumbers(bookingInfoDto.getNumOfRooms());
            bookingInfoEntity.setRoomNumbers(String.join(COMMA_DELIMITER, availableRooms));

            //business logic to calculate the price of rooms
            int noOfDays = BookingUtil.getNoOfDays(bookingInfoDto.getFromDate(), bookingInfoDto.getToDate());
            int roomPrice = PRICE_PER_ROOM * bookingInfoDto.getNumOfRooms() * noOfDays;
            bookingInfoEntity.setRoomPrice(roomPrice);

            //invoke dao class method to perform CRUD calls
            return bookingInfoDao.save(bookingInfoEntity);
        } catch (Exception e) {
            log.error("Exception Occurred while booking the room for {}", bookingInfoDto, e);
            return null;
        }
    }

    @Override
    public BookingInfoEntity doBookingTransaction(int bookingId, BookingTransactionDto bookingTransactionDto) {
        //validate PaymentMode
        validate(bookingTransactionDto.getPaymentMode());

        try {
            //invoke dao class method to fetch booking info based on ID
            Optional<BookingInfoEntity> bookingInfoEntityOptional = bookingInfoDao.findById(bookingTransactionDto.getBookingId());
            BookingInfoEntity bookingInfoEntity;
            if (bookingInfoEntityOptional.isPresent()) {
                //call payment service to get the transaction id for the transaction info
                Integer transactionId = restTemplate.postForObject(transactionUrl, bookingTransactionDto, Integer.class);
                if (transactionId == null) {
                    transactionId = 0;
                }
                bookingInfoEntity = bookingInfoEntityOptional.get();
                //invoke dao class method to perform CRUD calls
                bookingInfoEntity.setTransactionId(transactionId);
                bookingInfoEntity = bookingInfoDao.save(bookingInfoEntity);

                //notify the user via kafka
                publishMessage(transactionId, bookingInfoEntity);
                return bookingInfoEntity;
            } else {
                log.error("Booking with bookingId:{} is invalid", bookingId);
                throw new BookingInfoNotFoundException("Booking with id " + bookingId + " invalid");
            }
        } catch (RestClientException rce) {
            log.error("Exception while communicating with payment service", rce);
            throw rce;
        }
    }

    /**
     * method to publish the message onto Kafka topic
     *
     * @param transactionId - transaction id
     * @param bookingInfoEntity - entity object from db
     */
    private void publishMessage(Integer transactionId, BookingInfoEntity bookingInfoEntity) {
        String key = "Notification-" + transactionId;
        String value = "Booking confirmed for user with aadhaar number: " + bookingInfoEntity.getAadharNumber() + "    |    " + "Here are the booking details:    " + bookingInfoEntity.toString();
        producer.send(new ProducerRecord<>(topic, key, value));
    }

    /**
     * method to validate payment mode. Only CARD and UPI to be allowed
     * @param paymentMode - mode of payment
     */
    private void validate(String paymentMode) {
        if (paymentMode == null || (!paymentMode.equals(PAYMENT_CARD) && !paymentMode.equals(PAYMENT_UPI))) {
            log.error("Booking with paymentMode:{} not supported", paymentMode);
            throw new BookingServiceException("Payment mode " + paymentMode + " not supported");
        }
    }
}
