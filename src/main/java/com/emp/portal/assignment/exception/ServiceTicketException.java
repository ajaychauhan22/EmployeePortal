package com.emp.portal.assignment.exception;

/**
 * 
 * @author ajaychauhan01
 *
 */

public class ServiceTicketException extends Exception {

	private static final long serialVersionUID = 6185949696739672057L;

	private final String errorMessage;
	private final String errorCode;

	public ServiceTicketException(String errorMessage, String errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
