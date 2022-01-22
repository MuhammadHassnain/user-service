package com.hassnain.userservice.controller;


import com.hassnain.userservice.dto.CreateUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hassnain.userservice.dto.CreateUserRequest;
import com.hassnain.userservice.entity.User;
import com.hassnain.userservice.mapper.UserMapper;
import com.hassnain.userservice.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService userService;
	@Autowired UserMapper userMapper;
	
	
	@PostMapping
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
		
		
		logger.info("Received Create User Request: "+createUserRequest);
		
		User user = userMapper.CreateUserRequestToUser(createUserRequest);
		User createdUser = userService.createUser(user);

		return new ResponseEntity<>(userMapper.UserToCreateUserResponse(createdUser), HttpStatus.OK);
		
		
	}
	
}
