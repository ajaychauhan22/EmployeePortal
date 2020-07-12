package com.emp.portal.assignment.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 4485835681715174666L;

	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}
