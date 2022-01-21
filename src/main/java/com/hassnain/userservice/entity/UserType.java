package com.hassnain.userservice.entity;

public enum UserType {

	
	
	ADMIN("admin"),
	NORMAL("normal")
	;
	
	private final String name;
	
	UserType(String name){
		this.name = name;
	}

	/**
	 * @return the name of user type
	 */
	public String getName() {
		return name;
	}
}
