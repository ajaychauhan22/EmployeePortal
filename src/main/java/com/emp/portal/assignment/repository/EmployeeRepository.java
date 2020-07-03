package com.emp.portal.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emp.portal.assignment.entity.Employee;

/**
 * 
 * @author ajaychauhan01
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Employee findByEmail(String email);
}
