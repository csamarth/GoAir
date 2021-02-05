package com.example.passenger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.passenger.entity.Passenger;
import com.example.passenger.repository.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	public void createPassenger(List<Passenger> passengers) {

		passengerRepository.saveAll(passengers);

	}
	
}
