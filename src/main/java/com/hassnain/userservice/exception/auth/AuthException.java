package com.hassnain.userservice.exception.auth;

public class AuthException {


	public static class InvalidCredentialException extends RuntimeException{


		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public InvalidCredentialException() {
			super();
		}



	}

	public static class UserLockedException extends RuntimeException{


		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public UserLockedException(String email) {
			super(email);
		}



	}

}
