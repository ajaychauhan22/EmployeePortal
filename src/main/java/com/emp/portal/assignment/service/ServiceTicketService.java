package com.emp.portal.assignment.service;

import java.util.List;

import com.emp.portal.assignment.entity.ServiceTicket;
import com.emp.portal.assignment.exception.ServiceTicketException;

/**
 * 
 * @author ajaychauhan01
 *
 */
public interface ServiceTicketService {
	public ServiceTicket addTicket(ServiceTicket ticket);

	public List<ServiceTicket> findAllTickets();

	public ServiceTicket findTicket(Long ticketId) throws ServiceTicketException;

	public List<ServiceTicket> findAllTicketsForSpecificEmployee(Long employeeId) throws ServiceTicketException;

	public ServiceTicket findTicketForSpecificEmployee(Long employeeId, Long ticketId) throws ServiceTicketException;

	public ServiceTicket updateTicketForSpecificEmployee(ServiceTicket ticket) throws ServiceTicketException;

	public void removeTicket(Long ticketId) throws ServiceTicketException;
}
