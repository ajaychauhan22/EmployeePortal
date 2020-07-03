package com.emp.portal.assignment.controller;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.portal.assignment.config.util.Constants;
import com.emp.portal.assignment.entity.Employee;
import com.emp.portal.assignment.model.RestResponse;
import com.emp.portal.assignment.service.EmployeeAccountService;

/**
 * 
 * @author ajaychauhan01
 *
 */

@RestController
@RequestMapping("/employee")
public class EmployeeAccountController {

	private static final Logger log = LogManager.getLogger(EmployeeAccountController.class);

	@Autowired
	private EmployeeAccountService employeeAccountService;

	@PostMapping("/login")
	public ResponseEntity<RestResponse<String>> login(@Valid @RequestBody Employee employee, Errors errors) {
		log.info("Checking whether employee exists or not");

		try {
			if (errors.hasErrors()) {
				log.info("Required parameters are missing for logining employee");

				return new ResponseEntity<>(
						new RestResponse<String>(null, HttpStatus.BAD_REQUEST.toString(),
								errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
										.collect(Collectors.joining(Constants.VALIDATION_ERROR_SEPARATOR))),
						HttpStatus.BAD_REQUEST);
			}

			Employee existingEmployee = employeeAccountService.checkForAlreadyExistsEmployee(employee.getEmail());
			if (Objects.nonNull(existingEmployee) && existingEmployee.getPassword().equals(employee.getPassword())) {
				return new ResponseEntity<>(new RestResponse<String>(Constants.EMPLOYEE_LOGIN_SUCCESS,
						HttpStatus.OK.toString(), Constants.REQUEST_COMPLETED), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new RestResponse<String>(Constants.EMPLOYEE_LOGIN_FAIL,
						HttpStatus.BAD_REQUEST.toString(), Constants.REQUEST_FAIL), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("Exception in loging employee", e);
			return new ResponseEntity<>(new RestResponse<String>(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					Constants.ADMIN_CONTACT_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<RestResponse<String>> singUp(@Valid @RequestBody Employee employee, Errors errors) {
		log.info("Registering new Employee");

		try {
			if (errors.hasErrors()) {
				log.info("Required parameters are missing for registering employee");

				return new ResponseEntity<>(
						new RestResponse<String>(null, HttpStatus.BAD_REQUEST.toString(),
								errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
										.collect(Collectors.joining(Constants.VALIDATION_ERROR_SEPARATOR))),
						HttpStatus.BAD_REQUEST);
			}

			Employee existingEmployee = employeeAccountService.checkForAlreadyExistsEmployee(employee.getEmail());
			if (Objects.nonNull(existingEmployee)) {
				return new ResponseEntity<>(new RestResponse<String>(Constants.EMPLOYEE_REGISTER_FAIL,
						HttpStatus.BAD_REQUEST.toString(), Constants.REQUEST_FAIL), HttpStatus.BAD_REQUEST);
			} else {
				employeeAccountService.addEmployee(employee);
				return new ResponseEntity<>(new RestResponse<String>(Constants.EMPLOYEE_REGISTER_SUCCESS,
						HttpStatus.CREATED.toString(), Constants.REQUEST_COMPLETED), HttpStatus.CREATED);
			}

		} catch (Exception e) {
			log.error("Exception in creating employee", e);
			return new ResponseEntity<>(new RestResponse<String>(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					Constants.ADMIN_CONTACT_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}