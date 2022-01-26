package com.hassnain.userservice.exception.user;

import org.springframework.http.HttpStatus;

public enum UserExceptionType {
	
	USER_NOT_FOUND("User with email {0} doesn't found","Please use valid email",HttpStatus.NOT_FOUND),
	DUPLICATE_USER("Email {0} already in Use","Try again with new email", HttpStatus.BAD_REQUEST);
	
	private String message;
	private String action;
	private HttpStatus httpStatus;
	
	UserExceptionType(String message,String action,HttpStatus httpStatus) {
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
