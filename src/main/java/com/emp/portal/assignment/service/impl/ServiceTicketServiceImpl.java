package com.emp.portal.assignment.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.emp.portal.assignment.entity.Employee;
import com.emp.portal.assignment.entity.ServiceTicket;
import com.emp.portal.assignment.exception.ServiceTicketException;
import com.emp.portal.assignment.repository.EmployeeRepository;
import com.emp.portal.assignment.repository.ServiceTicketRepository;
import com.emp.portal.assignment.service.ServiceTicketService;

/**
 * 
 * @author ajaychauhan01
 *
 */
@Service
public class ServiceTicketServiceImpl implements ServiceTicketService {

	private static final Logger log = LogManager.getLogger(ServiceTicketServiceImpl.class);

	@Autowired
	ServiceTicketRepository serviceTicketRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	Environment env;

	@CachePut(value = "ticketCache", key = "#ticket.id")
	public ServiceTicket addTicket(ServiceTicket ticket) {
		
		ServiceTicket serviceTicket = serviceTicketRepository.save(ticket);
		log.info("new ticket added in system");
		return serviceTicket;
	
	}

	public List<ServiceTicket> findAllTickets() {
		return serviceTicketRepository.findAll();
	}

	@Cacheable(value = "ticketCache", key = "#ticketId")
	public ServiceTicket findTicket(Long ticketId) throws ServiceTicketException {
		
		return serviceTicketRepository.findById(ticketId)
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.message"),
						env.getProperty("no.ticket.found.code")));
	}

	public List<ServiceTicket> findAllTicketsForSpecificEmployee(Long employeeId) throws ServiceTicketException {
	
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.user.found.message"),
						env.getProperty("no.user.found.code")));
		return employee.getTicket();
	
	}

	public ServiceTicket findTicketForSpecificEmployee(Long employeeId, Long ticketId) throws ServiceTicketException {
	
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.user.found.message"),
						env.getProperty("no.user.found.code")));

		List<ServiceTicket> serviceTicketList = employee.getTicket();
		if (serviceTicketList == null) {
			log.info("invalid ticket list size");
			throw new ServiceTicketException(env.getProperty("no.ticket.found.for.user.message"),
					env.getProperty("no.ticket.found.for.user.code"));
		}

		return serviceTicketList.stream().filter(ticket -> ticket.getId().equals(ticketId)).findFirst()
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.for.user.message"),
						env.getProperty("no.ticket.found.for.user.code")));
	
	}

	@CachePut(value = "ticketCache", key = "#ticket.id")
	public ServiceTicket updateTicketForSpecificEmployee(ServiceTicket ticket) throws ServiceTicketException {
	
		ServiceTicket serviceTicket = serviceTicketRepository.findById(ticket.getId())
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.message"),
						env.getProperty("no.ticket.found.code")));

		serviceTicket.setDepartment(ticket.getDepartment());
		serviceTicket.setDetails(ticket.getDetails());
		log.info("ticket updated");
		return serviceTicketRepository.save(serviceTicket);

	}

	@CacheEvict(value = "ticketCache", key = "#ticketId", allEntries = false)
	public void removeTicket(Long ticketId) throws ServiceTicketException {
		
		ServiceTicket serviceTicket = serviceTicketRepository.findById(ticketId)
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.message"),
						env.getProperty("no.ticket.found.code")));
		log.info("ticket deleted");
		serviceTicketRepository.deleteById(serviceTicket.getId());
	
	}

}
