package com.emp.portal.assignment.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author ajaychauhan01
 *
 */
@Entity
@Table(name = "Employee", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email")
	@NotBlank(message = "Email is mandatory.")
	@ApiModelProperty(notes = "Email id", required = true)
	private String email;

	@Column(name = "firstname")
	@ApiModelProperty(notes = "First name", required = false)
	private String firstname;

	@Column(name = "lastname")
	@ApiModelProperty(notes = "Last name", required = false)
	private String lastname;

	@Column(name = "password")
	@NotBlank(message = "Password is mandatory.")
	@ApiModelProperty(notes = "Password", required = true)
	private String password;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeid")
	@ApiModelProperty(notes = "List of tickets of a Employee", required = false)
	private List<ServiceTicket> ticket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ServiceTicket> getTicket() {
		return ticket;
	}

	public void setTicket(List<ServiceTicket> ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", ticket=" + ticket + "]";
	}

}