package com.example.passenger.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.passenger.dto.SearchFlights;

@FeignClient("FlightMS")
public interface PassFlightFeign {
	
	@RequestMapping(value="/flights/{flightId}")
	public SearchFlights getFlight(@PathVariable("flightId") String flightId);

}
