package com.emp.portal.assignment.model;


/**
 * 
 * @author ajaychauhan01
 *
 * @param <T>
 */
public class RestResponse<T> {
	private T data;
	private String status;
	private String message;

	public RestResponse() {
	}

	public RestResponse(final T data) {
		this.data = data;
	}

	public RestResponse(final T data, final String status, final String message) {
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
