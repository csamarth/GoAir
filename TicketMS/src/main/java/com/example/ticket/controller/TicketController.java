package com.example.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket.dto.TicketDetails;
import com.example.ticket.service.TicketService;

@RestController
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping(value = "/tickets")
	public void addTicket(@RequestBody TicketDetails ticketDetails) {
		System.out.println(ticketDetails);
		ticketService.createTicket(ticketDetails);
	}

}
