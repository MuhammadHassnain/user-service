package com.hassnain.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CreateUserRequest {
	

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String createdBy;
	
	
}
