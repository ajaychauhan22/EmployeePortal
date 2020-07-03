package com.emp.portal.assignment.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.portal.assignment.entity.Employee;
import com.emp.portal.assignment.repository.EmployeeRepository;
import com.emp.portal.assignment.service.impl.EmployeeAccountServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeAccountServiceTest {

	@InjectMocks
	private EmployeeAccountServiceImpl employeeAccountServiceImpl;

	private EmployeeRepository employeeRepository;

	@Before
	public void init() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addEmployee_test_should_verify_call() {

		Mockito.when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(null);
		employeeAccountServiceImpl.addEmployee(new Employee());

		Mockito.verify(employeeRepository, Mockito.times(1)).save(ArgumentMatchers.any(Employee.class));
	}

	@Test
	public void checkForAlreadyExistsEmployee_test_should_verify() {

		Employee e = new Employee();
		e.setId(1L);
		e.setEmail("ajay.chauhan01@nagarro.com");
		e.setFirstname("Ajay");
		e.setLastname("Chauhan");

		Mockito.when(employeeRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(e);
		Employee eDB = employeeAccountServiceImpl.checkForAlreadyExistsEmployee("ajay.chauhan01@nagarro.com");

		Mockito.verify(employeeRepository, Mockito.times(1)).findByEmail(ArgumentMatchers.anyString());

		org.junit.Assert.assertEquals(eDB.getEmail(), e.getEmail());
	}
}
