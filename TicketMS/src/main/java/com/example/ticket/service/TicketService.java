package com.example.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticket.dto.TicketDetails;
import com.example.ticket.entity.Ticket;
import com.example.ticket.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public void createTicket(TicketDetails ticketDetails) {
		Ticket ticket = ticketDetails.createTicket();
		ticketRepository.saveAndFlush(ticket);

	}

}
