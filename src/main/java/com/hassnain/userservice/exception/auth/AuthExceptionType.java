package com.hassnain.userservice.exception.auth;

import org.springframework.http.HttpStatus;

public enum AuthExceptionType {

	USER_LOCKED("{0} is locked","Please contact support",HttpStatus.LOCKED),
	INVALID_CREDENTIAL("Credential is not valid","Please check  your credential", HttpStatus.UNAUTHORIZED);

	private String message;
	private String action;
	private HttpStatus httpStatus;

	AuthExceptionType(String message, String action, HttpStatus httpStatus) {
		this.message = message;
		this.action = action;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getAction() {
		return action;
	}
	
	
	
	
}
