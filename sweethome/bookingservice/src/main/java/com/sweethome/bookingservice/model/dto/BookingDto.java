package com.sweethome.bookingservice.model.dto;

import java.util.Date;

import com.sweethome.bookingservice.model.entity.BookingInfoEntity;

public class BookingDto {

	private Date fromDate;
	private Date toDate;
	private String aadharNumber;
	private int numOfRooms;
	public BookingDto() {
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public BookingDto(Date fromDate, Date toDate, String aadharNumber, int numOfRooms) {

		this.fromDate = fromDate;
		this.toDate = toDate;
		this.aadharNumber = aadharNumber;
		this.numOfRooms = numOfRooms;

	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
