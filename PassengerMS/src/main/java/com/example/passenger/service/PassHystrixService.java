package com.example.passenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.passenger.controller.PassFlightFeign;
import com.example.passenger.dto.SearchFlights;
import com.example.passenger.dto.TicketDetails;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PassHystrixService {
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	PassFlightFeign flightFeign;

	@HystrixCommand
	public SearchFlights getFlight(String flightId) {
		return flightFeign.getFlight(flightId);
	}
	
	@HystrixCommand
	public String createTicket(TicketDetails ticketDetails) {
		return template.postForObject("http://TICKETMS/tickets/", ticketDetails, String.class);
	}
	
	@HystrixCommand
	public String updateFlight(String flightId, int noOfSeats) {
		return template.getForObject("http://FLIGHTMS/flights/" + flightId + "/" + noOfSeats, String.class);
	}
	
//	@HystrixCommand
//	public Future<SearchFlights> getFlight(String flightId) {
//		return new AsyncResult<SearchFlights>() {
//			@Override
//			public SearchFlights invoke() {
//				return template.getForObject("http://FLIGHTMS" + "/flights/" + flightId, SearchFlights.class);
//			}
//		};
//	}
//	
//	@HystrixCommand
//	public Future<String> createTicket(TicketDetails ticketDetails) {
//		return new AsyncResult<String>() {
//			@Override
//			public String invoke() {
//				return template.postForObject("http://TICKETMS/flights/", ticketDetails, String.class);
//			}
//		};
//	}
//	
//	@HystrixCommand
//	public Future<String> updateFlight(String flightId, int noOfSeats) {
//		return new AsyncResult<String>() {
//			@Override
//			public String invoke() {
//				return template.getForObject("http://FLIGHTMS/flights/" + flightId + "/" + noOfSeats, String.class);
//			}
//		};
//	}
}
