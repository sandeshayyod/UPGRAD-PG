package com.sweethome.bookingservice.service;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sweethome.bookingservice.exceptions.CustomException;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sweethome.bookingservice.dao.BookiningDao;
import com.sweethome.bookingservice.model.dto.BookingDto;
import com.sweethome.bookingservice.model.dto.PaymentDto;
import com.sweethome.bookingservice.model.entity.BookingInfoEntity;

@Service
public class BookingService {
	
	@Autowired
	public BookingService(BookiningDao bookingDao, RestTemplate restTemplate, Producer<String, String> producer) {
		this.bookingDao = bookingDao;
		this.restTemplate = restTemplate;
		this.producer = producer;
		
	}
	
	BookiningDao bookingDao;
	RestTemplate restTemplate;
	Producer<String, String> producer;
	private int pricePerRoomPerDay = 1000;


	@Value("${url.service.payment}")
	private String paymentServiceUrl;

	public static ArrayList<String> getRoomNumbers(int numOfRooms){
		Random rand = new Random();
		int upperBound = 100;
		ArrayList<String> roomList = new ArrayList<String>();
		for (int i=0; i<numOfRooms; i++){
			roomList.add(String.valueOf(rand.nextInt(upperBound)));
		}
		return roomList;
	}

	public BookingInfoEntity  bookingDetails(BookingDto bookingRequest) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		int requestedNumOfRooms = bookingRequest.getNumOfRooms();

		String roomNumbers = String.join(",", getRoomNumbers(requestedNumOfRooms));
		BookingInfoEntity bookingInfo = new BookingInfoEntity();
		bookingInfo.setRoomNumbers(roomNumbers);
		bookingInfo.setFromDate(bookingRequest.getFromDate());
		bookingInfo.setToDate(bookingRequest.getToDate());
		bookingInfo.setBookedOn(date);
		bookingInfo.setAadharNumber(bookingRequest.getAadharNumber());
		long numberOfDays = (bookingInfo.getToDate().getTime() - bookingRequest.getFromDate().getTime())/ (1000*60*60*24) % 365;
		System.out.println("Number of Days: " + numberOfDays);
		bookingInfo.setRoomPrice((int) (bookingRequest.getNumOfRooms()* pricePerRoomPerDay* numberOfDays));
		System.out.println(bookingInfo.toString());
		return bookingDao.save(bookingInfo);
	}

	public BookingInfoEntity doPayment( PaymentDto paymentDetails) throws Exception {

		// call payment service and get paymentID to save in booking.
		System.out.println(paymentDetails.toString());
		String url = this.paymentServiceUrl + "/transaction" ;

		if(!(paymentDetails.getPaymentMode().trim().equalsIgnoreCase("UPI") | paymentDetails.getPaymentMode().trim().equalsIgnoreCase("CARD"))){
			throw new CustomException("Invalid mode of payment");
		}
		int bookingId = paymentDetails.getBookingId();
		Optional<BookingInfoEntity> bookingInfoOptional = bookingDao.findById(bookingId);
		if(bookingInfoOptional.isPresent()) {
			BookingInfoEntity bookingInfo = bookingInfoOptional.get();
			int trancationId = restTemplate.postForObject(url, paymentDetails, Integer.class);
			bookingInfo.setTrancationId(trancationId);
			bookingDao.save(bookingInfo);
			String message = "Booking confirmed for user with aadhaar number: " + bookingInfo.getAadharNumber() +
					"    |    " + "Here are the booking details:    " + bookingInfo.toString();
			producer.send(new ProducerRecord<String, String>("message","message", message));
			return bookingInfo;
		}else {
			throw new CustomException("Invalid Booking Id");
		}
	}

}
