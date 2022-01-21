package com.hassnain.userservice.exception;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hassnain.core.exception.ExceptionResponse;
import com.hassnain.userservice.exception.UserException.DuplicateEmailException;
import com.hassnain.userservice.exception.UserException.UserNotFoundException;


@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
	
		private final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);
		
		@ExceptionHandler(UserNotFoundException.class)
		public final ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException exception){
			
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setMessage(MessageFormat.format(UserExceptionType.USER_NOT_FOUND.getMessage(),exception.getMessage()));
			exceptionResponse.setAction(UserExceptionType.USER_NOT_FOUND.getAction());
			return new ResponseEntity<ExceptionResponse>(exceptionResponse,UserExceptionType.USER_NOT_FOUND.getHttpStatus());
		}
		

		@ExceptionHandler(DuplicateEmailException.class)
		public final ResponseEntity<ExceptionResponse> duplicateEmail(DuplicateEmailException exception){
			
			logger.info("Exception duplicate Email" + exception.getMessage());
			
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setMessage(MessageFormat.format(UserExceptionType.DUPLICATE_USER.getMessage(),exception.getMessage()));
			exceptionResponse.setAction(UserExceptionType.DUPLICATE_USER.getAction());
			return new ResponseEntity<ExceptionResponse>(exceptionResponse,UserExceptionType.DUPLICATE_USER.getHttpStatus());
		}

		
	}

