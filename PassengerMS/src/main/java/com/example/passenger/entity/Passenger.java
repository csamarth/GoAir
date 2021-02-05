package com.example.passenger.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "PASSENGER_DETAILS")

public class Passenger {

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")

	private int passengerId;
	private String passengerName;
	private String passengerAge;
	private String passengerGender;
	private int ticketPnr;


	public int getTicketPnr() {
		return ticketPnr;
	}

	public void setTicketPnr(int ticketPnr) {
		this.ticketPnr = ticketPnr;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(String passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}


	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", passengerGender=" + passengerGender + ", ticketPnr=" + ticketPnr+ "]";
	}

}