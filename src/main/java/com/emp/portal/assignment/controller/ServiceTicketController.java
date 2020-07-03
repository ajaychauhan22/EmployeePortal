package com.emp.portal.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.portal.assignment.entity.ServiceTicket;
import com.emp.portal.assignment.exception.ServiceTicketException;
import com.emp.portal.assignment.service.ServiceTicketService;

/**
 * 
 * @author ajaychauhan01
 *
 */
@RestController
@RequestMapping("/ticket")
public class ServiceTicketController {

	private static final Logger log = LogManager.getLogger(ServiceTicketController.class);

	@Autowired
	private ServiceTicketService serviceTicketService;

	@PostMapping(value = "/add")
	public ResponseEntity<ServiceTicket> addTicket(@Valid @RequestBody ServiceTicket ticket) {
		log.info("Adding ticket a new ticket.");

		log.debug("Ticket details are as {} ", ticket);

		return new ResponseEntity<>(serviceTicketService.addTicket(ticket), HttpStatus.CREATED);
	}

	@GetMapping(value = "/tickets")
	public ResponseEntity<List<ServiceTicket>> findAllTickets() {
		log.info("Fetch all tickets");

		return ResponseEntity.ok().body(serviceTicketService.findAllTickets());
	}

	@GetMapping(value = "/ticket-id/{ticketId}")
	public ResponseEntity<ServiceTicket> findTicketByTicketId(@PathVariable("ticketId") Long ticketId)
			throws ServiceTicketException {
		log.info("Get a ticket by ticket id {} ", ticketId);

		return ResponseEntity.ok().body(serviceTicketService.findTicket(ticketId));
	}

	@GetMapping(value = "/employee-id/{employeeId}")
	public ResponseEntity<List<ServiceTicket>> findAllTicketsForSpecificUser(
			@PathVariable("employeeId") Long employeeId) throws ServiceTicketException {
		log.info("fetch tickets for a user {} operation called ", employeeId);
		return ResponseEntity.ok().body(serviceTicketService.findAllTicketsForSpecificEmployee(employeeId));
	}

	@GetMapping(value = "/{employeeId}/{ticketId}")
	public ResponseEntity<ServiceTicket> findTicketForSpecificUser(@PathVariable("employeeId") Long employeeId,
			@PathVariable("ticketId") Long ticketId) throws ServiceTicketException {
		log.info("Fetching ticket for ticket id {} for userid {} operation called ", ticketId, employeeId);

		return ResponseEntity.ok().body(serviceTicketService.findTicketForSpecificEmployee(employeeId, ticketId));
	}

	@PutMapping(value = "/{ticketId}")
	public ResponseEntity<ServiceTicket> updateTicket(@PathVariable("ticketId") Long ticketId,
			@Valid @RequestBody ServiceTicket ticket) throws ServiceTicketException {
		log.info("Update ticket details for ticket id {} ", ticketId);
		return ResponseEntity.ok().body(serviceTicketService.updateTicketForSpecificEmployee(ticket));
	}

	@DeleteMapping(value = "/{ticketId}")
	public ResponseEntity<String> removeTicket(@PathVariable("ticketId") Long ticketId) throws ServiceTicketException {
		log.info("Delete ticket for Id {} ", ticketId);

		serviceTicketService.removeTicket(ticketId);
		return ResponseEntity.ok().body("Ticket removed.");
	}

}
