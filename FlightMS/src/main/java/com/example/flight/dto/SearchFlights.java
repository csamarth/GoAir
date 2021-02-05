package com.example.flight.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.flight.entity.Flight;

public class SearchFlights {

	@NotEmpty(message = "Please enter the origin")
	@NotNull(message = "Soource is mandatory")
	private String source;

	@NotEmpty(message = "Please enter the destination")
	@NotNull(message = "Destination is mandatory")
	private String destination;
	@NotEmpty(message = "Please enter the journey date")
	@NotNull(message = "Journey date is mandatory")
	private Date journeyDate;

	private Double fare;
	private String flightId;

	private Integer seatCount;
	private String departureTime;
	private String arrivalTime;
	private String airlines;

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date calendar) {
		this.journeyDate = calendar;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	//Converts Entity into DTO
	public static SearchFlights valueOf(Flight flight) {
		SearchFlights searchFlights = new SearchFlights();
		searchFlights.setFare(flight.getFare());
		searchFlights.setSeatCount(flight.getSeatCount());
		searchFlights.setAirlines(flight.getAirlines());
		searchFlights.setArrivalTime(flight.getArrivalTime());
		searchFlights.setDepartureTime(flight.getDepartureTime());
		searchFlights.setDestination(flight.getDestination());
		searchFlights.setFlightId(flight.getFlightId());
		searchFlights.setSource(flight.getSource());
		searchFlights.setJourneyDate(flight.getFlightAvailableDate());
		return searchFlights;
	}

}
