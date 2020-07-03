package com.emp.portal.assignment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.portal.assignment.entity.Employee;
import com.emp.portal.assignment.entity.ServiceTicket;
import com.emp.portal.assignment.exception.ServiceTicketException;
import com.emp.portal.assignment.repository.EmployeeRepository;
import com.emp.portal.assignment.repository.ServiceTicketRepository;
import com.emp.portal.assignment.service.impl.ServiceTicketServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTicketServiceTest {

	@InjectMocks
	private ServiceTicketServiceImpl serviceTicketService;

	private EmployeeRepository employeeRepository;
	private ServiceTicketRepository serviceTicketRepository;

	private ServiceTicket serviceTicket = new ServiceTicket();
	private Employee e = new Employee();

	@Before
	public void init() {
		e.setId(1L);
		e.setEmail("ajay.chauhan01@nagarro.com");
		e.setFirstname("Ajay");
		e.setLastname("Chauhan");
		e.setTicket(new ArrayList<ServiceTicket>());

		serviceTicket.setDepartment("IT");
		serviceTicket.setDetails("Software installation");
		serviceTicket.setEmployeeId(1L);
		serviceTicket.setId(1L);

		employeeRepository = Mockito.mock(EmployeeRepository.class);
		serviceTicketRepository = Mockito.mock(ServiceTicketRepository.class);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addTicket_test_should_retrun_serviceTicket_object() {
		Mockito.when(serviceTicketRepository.save(ArgumentMatchers.any(ServiceTicket.class))).thenReturn(null);
		serviceTicketService.addTicket(new ServiceTicket());

		Mockito.verify(serviceTicketRepository, Mockito.times(1)).save(ArgumentMatchers.any(ServiceTicket.class));
	}

	@Test
	public void findAllTickets_test_should_retrun_list_of_size_1() {
		List<ServiceTicket> sl = Arrays.asList(serviceTicket);

		Mockito.when(serviceTicketRepository.findAll()).thenReturn(sl);
		List<ServiceTicket> slDB = serviceTicketService.findAllTickets();

		Mockito.verify(serviceTicketRepository, Mockito.times(1)).findAll();

		org.junit.Assert.assertEquals(1, slDB.size());
	}

	@Test
	public void findTicket_test_should_retrun_ticket() throws ServiceTicketException {

		Mockito.when(serviceTicketRepository.findById(1L)).thenReturn(Optional.<ServiceTicket>of(serviceTicket));
		ServiceTicket sDB = serviceTicketService.findTicket(1L);

		Mockito.verify(serviceTicketRepository, Mockito.times(1)).findById(1L);

		org.junit.Assert.assertEquals(new Long(1), sDB.getId());
	}

	@Test
	public void findAllTicketsForSpecificEmployee_test_should_retrun_empty_ticket_list() throws ServiceTicketException {

		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.<Employee>of(e));
		List<ServiceTicket> sDB = serviceTicketService.findAllTicketsForSpecificEmployee(1L);

		Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);

		org.junit.Assert.assertEquals(0, sDB.size());
	}

	@Test
	public void findTicketForSpecificEmployee_test_should_retrun_service_ticket() throws ServiceTicketException {
		e.getTicket().add(serviceTicket);
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.<Employee>of(e));
		ServiceTicket sDB = serviceTicketService.findTicketForSpecificEmployee(1L, 1L);

		Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);

		org.junit.Assert.assertEquals("IT", sDB.getDepartment());
	}

	@Test
	public void updateTicketForSpecificEmployee_test_should_retrun_service_ticket() throws ServiceTicketException {

		Mockito.when(serviceTicketRepository.findById(1L)).thenReturn(Optional.<ServiceTicket>of(serviceTicket));
		serviceTicketService.updateTicketForSpecificEmployee(serviceTicket);

		Mockito.verify(serviceTicketRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(serviceTicketRepository, Mockito.times(1)).save(serviceTicket);
	}

	@Test
	public void removeTicket_should_verify_delete_call_one_time() throws ServiceTicketException {

		Mockito.when(serviceTicketRepository.findById(1L)).thenReturn(Optional.<ServiceTicket>of(serviceTicket));
		Mockito.doNothing().when(serviceTicketRepository).deleteById(1L);
		serviceTicketService.removeTicket(1L);

		Mockito.verify(serviceTicketRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(serviceTicketRepository, Mockito.times(1)).deleteById(1L);
	}
}
