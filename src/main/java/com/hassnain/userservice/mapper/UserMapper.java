package com.sharetaxi.userservice.mapper;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sharetaxi.userservice.dto.CreateUserRequest;
import com.sharetaxi.userservice.entity.User;
import com.sharetaxi.userservice.entity.UserType;

@Component
public class UserMapper {
	
	@Autowired ModelMapper modelMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);
	
	public User CreateUserRequestToUser(CreateUserRequest createUserRequest) {
		User user = modelMapper.map(createUserRequest, User.class);
		user.setCreatedBy(user.getFirstName() + " "+ user.getLastName());
		user.setUpdatedBy(user.getCreatedBy());
		user.setUserType(UserType.NORMAL);
		
		logger.info("Mapped Create User request to: "+user);
		return user;
	}

}
