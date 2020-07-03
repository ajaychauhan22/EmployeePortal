package com.emp.portal.assignment.exception;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author ajaychauhan01
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceTicketExceptionConverter {

	private String errorMessage;
	private String errorCode;
	private long timeStamp;
	private Map<String, Object> errorMap;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Map<String, Object> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, Object> errorMap) {
		this.errorMap = errorMap;
	}

}
