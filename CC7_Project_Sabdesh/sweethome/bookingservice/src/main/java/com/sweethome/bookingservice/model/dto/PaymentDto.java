package com.sweethome.bookingservice.model.dto;

public class PaymentDto {
	private int bookingId;
	private String paymentMode;
	private String upiId;
	private String cardNumber;


	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public String toString() {
		return "PaymentDto{" +
				"bookingId=" + bookingId +
				", paymentMode='" + paymentMode + '\'' +
				", upiId='" + upiId + '\'' +
				", cardNumber='" + cardNumber + '\'' +
				'}';
	}
}
