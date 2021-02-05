package com.example.flight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flight.dto.SearchFlights;
import com.example.flight.entity.Flight;
import com.example.flight.exception.ARSServiceException;
import com.example.flight.repository.FlightsRepository;


@Service
public class FlightService {

	@Autowired
	private FlightsRepository flightsRepository;

	public List<String> getSources() throws ARSServiceException {
		List<String> sources = flightsRepository.findFlightSources();
		if (sources == null) {
			throw new ARSServiceException("No details available");
		} else {
			return sources;
		}
	}

	public List<String> getDestinationss() throws ARSServiceException {
		List<String> destinations = flightsRepository.findFlightDestinations();
		if (destinations == null) {
			throw new ARSServiceException("No details available");
		} else {
			return destinations;
		}
	}

	public List<SearchFlights> getFlights(String source, String destination, Date journeyDate) {

		List<Flight> flights = flightsRepository.findFlightDetails(source, destination, journeyDate);

		List<SearchFlights> availableFlights = new ArrayList<SearchFlights>();
		for (Flight f : flights) {
			SearchFlights flight = SearchFlights.valueOf(f);
//			flight.setFlightId(f.getFlightId());
//			flight.setSource(f.getSource());
//			flight.setDestination(f.getDestination());
//			flight.setJourneyDate(f.getFlightAvailableDate());
//			flight.setDepartureTime(f.getDepartureTime());
//			flight.setArrivalTime(f.getArrivalTime());
//			flight.setSeatCount(f.getSeatCount().toString());
//			flight.setAirlines(f.getAirlines());
//			flight.setFare(Double.toString(f.getFare()));
			availableFlights.add(flight);
		}

		return availableFlights;

	}

	public void updateFlight(String flightId, int noOfSeats) throws ARSServiceException {
		Flight flight = flightsRepository.findById(flightId).get();

		if (flight == null) {
			throw new ARSServiceException("No flight for the given details");
		} else {

			int count = flight.getSeatCount() - Integer.valueOf(noOfSeats);
			flight.setSeatCount(count);
			flightsRepository.saveAndFlush(flight);

		}

	}

	public SearchFlights getFlights(String flightId) {
		SearchFlights searchFlights = null;
		Optional<Flight> optFlight = flightsRepository.findById(flightId);
		if (optFlight.isPresent()) {
			Flight flight = optFlight.get();
			searchFlights = SearchFlights.valueOf(flight);
		}
		return searchFlights;

	}

}
