package com.hassnain.userservice.exception;

public class UserException {
	
	
	public static class UserNotFoundException extends RuntimeException{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(String email) {
			super(email);
		}
		
		
		
	}

	public static class DuplicateEmailException extends RuntimeException{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DuplicateEmailException(String email) {
			super(email);
		}
		
		
		
	}

}
