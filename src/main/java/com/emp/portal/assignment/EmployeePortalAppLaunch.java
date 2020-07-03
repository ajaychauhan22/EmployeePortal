package com.emp.portal.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 
 * @author ajaychauhan01
 *
 */
@SpringBootApplication
@EnableCaching
public class EmployeePortalAppLaunch {
	public static void main(String[] args) {
		SpringApplication.run(EmployeePortalAppLaunch.class, args);
	}
}
