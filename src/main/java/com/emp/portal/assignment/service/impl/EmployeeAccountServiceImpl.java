package com.emp.portal.assignment.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.portal.assignment.entity.Employee;
import com.emp.portal.assignment.repository.EmployeeRepository;
import com.emp.portal.assignment.service.EmployeeAccountService;

/**
 * 
 * @author ajaychauhan01
 *
 */
@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

	@Autowired
	EmployeeRepository employeeRepository;

	public void addEmployee(@Valid Employee employee) {
		employeeRepository.save(employee);
	}

	public Employee checkForAlreadyExistsEmployee(String email) {
		return employeeRepository.findByEmail(email);
	}

}
