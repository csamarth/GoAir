package com.example.passenger.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.passenger.dto.BookingDetails;
import com.example.passenger.dto.PassengerDetails;
import com.example.passenger.dto.SearchFlights;
import com.example.passenger.dto.TicketDetails;
import com.example.passenger.entity.Passenger;
import com.example.passenger.exception.ARSServiceException;
import com.example.passenger.exception.AirGoServiceException;
import com.example.passenger.exception.ExceptionConstants;
import com.example.passenger.service.PassHystrixService;
import com.example.passenger.service.PassengerService;
import com.example.passenger.utility.ClientErrorInformation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value="/passenger")
//@RibbonClient(name="passribbon")
public class PassengerController {
	
	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	PassHystrixService hystService;
	
//	@Autowired
//	DiscoveryClient client;
	
	protected Logger logger = Logger.getLogger(PassengerController.class.getName());
	
	private int pnr;
	private int noOfSeats;
	private TicketDetails ticketDetails;
	
	public PassengerController() {
		ticketDetails = new TicketDetails();
	}
	
//	@HystrixCommand(fallbackMethod = "bookFlightFallBack")
	@PostMapping(value = "/{flightId}/{username}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BookingDetails> bookFlight(@PathVariable("flightId") String flightId,
		 @Valid @RequestBody PassengerDetails passengerDetails, @PathVariable("username") String username,Errors errors) throws AirGoServiceException, ARSServiceException, InterruptedException, ExecutionException {
			
		    if (errors.hasErrors()) {
			return new ResponseEntity(new ClientErrorInformation(HttpStatus.BAD_REQUEST.value(),errors.getFieldError("passengerList").getDefaultMessage()), HttpStatus.BAD_REQUEST);
		    }
		if(passengerDetails.getPassengerList().isEmpty())
        	throw new AirGoServiceException(ExceptionConstants.PASSENGER_LIST_EMPTY.toString());
        	
		List<Passenger> passengerList = new ArrayList<Passenger>();
		for (Passenger passengers : passengerDetails.getPassengerList()) {
			passengerList.add(passengers);
		    

		}
		System.out.println(passengerList.toString());

		logger.log(Level.INFO, "Book Flight method ");

		logger.log(Level.INFO, passengerDetails.toString());
		pnr = (int) (Math.random() * 1858955);

		//ticket.setPnr(pnr);
		
//		Date date = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
          
		//Flight flight = flightService.getFlights(flightId);
		//call flight controller to get flights
		
//		List<ServiceInstance> flightInstance = client.getInstances("FLIGHTMS");
//		URI flightURI = flightInstance.get(0).getUri();
		
//		Future<SearchFlights> searchFlightFuture = hystService.getFlight(flightId);
		SearchFlights searchFlight = hystService.getFlight(flightId);

		double fare = searchFlight.getFare();
		System.out.println("Fare per person:****** " + fare);
		System.out.println("List size:****** " + passengerDetails.getPassengerList().size());
		double totalFare = fare * (passengerDetails.getPassengerList().size());

		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setPassengerList(passengerDetails.getPassengerList());
		bookingDetails.setPnr(pnr);
		bookingDetails.setTotalFare(totalFare);
		ticketDetails.setBookingDate(new Date());
		System.out.println(ticketDetails.getBookingDate());
		ticketDetails.setDepartureDate(searchFlight.getJourneyDate());
		ticketDetails.setDepartureTime(searchFlight.getDepartureTime());
		ticketDetails.setFlightId(searchFlight.getFlightId());
		ticketDetails.setUserId(username);		
		ticketDetails.setTotalFare(totalFare);
		ticketDetails.setPnr(pnr);
		noOfSeats = passengerDetails.getPassengerList().size();
		ticketDetails.setNoOfSeats(noOfSeats);
		
		logger.log(Level.INFO, ticketDetails.toString());
		String response = hystService.createTicket(ticketDetails);
//		String response = new RestTemplate().postForObject("http://localhost:8500/tickets/", ticketDetails, String.class);
		logger.log(Level.INFO, response);
    
		addPassengers(bookingDetails.getPassengerList());
		
		hystService.updateFlight(flightId, noOfSeats);
//		new RestTemplate().getForObject("http://localhost:8300/flights/" + flightId + "/" + noOfSeats, String.class);
		//flightService.updateFlight(flightId, noOfSeats);

		return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.OK);

	}
	
	public ResponseEntity<BookingDetails> bookFlightFallBack(@PathVariable("flightId") String flightId,
			 @Valid @RequestBody PassengerDetails passengerDetails, @PathVariable("username") String username,Errors errors){
		return new ResponseEntity<BookingDetails>(new BookingDetails(), HttpStatus.FAILED_DEPENDENCY);
	}

	private void addPassengers(List<Passenger> passengers) {
		
		for (Passenger passenger : passengers) {
			passenger.setTicketPnr(pnr);	    

		}

		passengerService.createPassenger(passengers);

	}
	
}
