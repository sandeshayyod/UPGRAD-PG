package com.sweethome.bookingservice.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;



@Table(name = "hotel_booking")
@Entity
public class BookingInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date fromDate;
	private Date toDate;
	private String aadharNumber;
	private String roomNumbers;

	@NotNull
	private int roomPrice;

	private int trancationId;
//	@Temporal(TemporalType.DATE)
//	@CreatedDate
	private Date bookedOn;


	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public BookingInfoEntity() {
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public int getTrancationId() {
		return trancationId;
	}
	public void setTrancationId(int trancationId) {
		this.trancationId = trancationId;
	}
	public Date getBookedOn() {
		return bookedOn;
	}
	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}
	public int getId() {
		return id;
	}
	public String getRoomNumbers() {
		return roomNumbers;
	}
	public void setRoomNumbers(String roomNumbers) {
		this.roomNumbers = roomNumbers;
	}

	@Override
	public String toString() {
		return "BookingInfoEntity{" +
				"fromDate=" + fromDate +
				", toDate=" + toDate +
				", aadharNumber='" + aadharNumber + '\'' +
				", roomNumbers='" + roomNumbers + '\'' +
				", roomPrice=" + roomPrice +
				", trancationId='" + trancationId + '\'' +
				", bookedOn=" + bookedOn +
				'}';
	}
}
