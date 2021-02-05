package com.example.passenger.dto;

import java.util.Date;

public class TicketDetails {
	
	private int pnr;
	private Date bookingDate;
	private String departureTime;
	private Date departureDate;
	private double totalFare;
	private String flightId;
	private String userId;
	private int noOfSeats;
	public int getPnr() {
		return pnr;
	}
	public void setPnr(int pnr) {
		this.pnr = pnr;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	@Override
	public String toString() {
		return "TicketDetails [bookingDate=" + bookingDate + ", departureTime=" + departureTime + ", departureDate="
				+ departureDate + ", totalFare=" + totalFare + ", ticketPnr=" + pnr + ", totalFare=" + totalFare + ", userId=" + userId + ", noOfSeats=" + noOfSeats + "]";
	}
}
