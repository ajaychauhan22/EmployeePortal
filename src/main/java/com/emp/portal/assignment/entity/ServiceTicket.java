package com.emp.portal.assignment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author ajaychauhan01
 *
 */

@Entity
@Table(name = "Ticket")
public class ServiceTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Ticket Id")
	private Long id;

	@Column(name = "department")
	@NotBlank(message = "Department is mandatory.")
	@ApiModelProperty(notes = "Department value", required = true)
	private String department;

	@Column(name = "details")
	@NotBlank(message = "Details are mandatory.")
	@ApiModelProperty(notes = "Ticket details", required = true)
	private String details;

	@Column(name = "createDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@ApiModelProperty(notes = "Ticket creation date")
	private Date createDate = new Date();

	@Column(name = "employeeid")
	@NotNull(message = "Employee id cannot be blank")
	@ApiModelProperty(notes = "Employee id", required = true)
	private Long employeeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "ServiceTicket [id=" + id + ", department=" + department + ", details=" + details + ", createDate="
				+ createDate + ", employeeId=" + employeeId + "]";
	}

}