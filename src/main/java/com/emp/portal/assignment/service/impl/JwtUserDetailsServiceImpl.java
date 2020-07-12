package com.emp.portal.assignment.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emp.portal.assignment.entity.Employee;
import com.emp.portal.assignment.service.EmployeeAccountService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeAccountService employeeAccountService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Employee e = employeeAccountService.checkForAlreadyExistsEmployee(username);
			return new User(e.getEmail(), passwordEncoder.encode(e.getPassword()),
					new ArrayList<>());
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
