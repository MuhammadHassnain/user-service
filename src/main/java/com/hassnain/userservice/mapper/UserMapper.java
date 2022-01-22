package com.hassnain.userservice.mapper;


import com.hassnain.userservice.dto.CreateUserResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hassnain.userservice.dto.CreateUserRequest;
import com.hassnain.userservice.entity.User;
import com.hassnain.userservice.entity.UserType;

import java.util.Collections;

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


	public UserDetails UserToUserDetail(User user){
		return
				new org.springframework.security.core.userdetails.User(user.getEmail(),
						user.getPassword(),
						true,true,true,true,
						Collections.singleton(new SimpleGrantedAuthority(user.getUserType().convertToRole())));
	}


	public CreateUserResponse UserToCreateUserResponse(User user){
		return modelMapper.map(user, CreateUserResponse.class);
	}
}
