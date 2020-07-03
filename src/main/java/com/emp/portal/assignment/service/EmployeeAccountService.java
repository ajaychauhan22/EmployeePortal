package com.emp.portal.assignment.service;

import com.emp.portal.assignment.entity.Employee;

/**
 * 
 * @author ajaychauhan01
 *
 */
public interface EmployeeAccountService {
	public void addEmployee(Employee employee);

	public Employee checkForAlreadyExistsEmployee(String email);
}
