package com.sharetaxi.userservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharetaxi.userservice.dto.CreateUserRequest;
import com.sharetaxi.userservice.entity.User;
import com.sharetaxi.userservice.mapper.UserMapper;
import com.sharetaxi.userservice.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService userService;
	@Autowired UserMapper userMapper;
	
	
	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest createUserRequest) {
		
		
		logger.info("Received Create User Request: "+createUserRequest);
		
		User user = userMapper.CreateUserRequestToUser(createUserRequest);
		User createdUser = userService.createUser(user);
		
		return new ResponseEntity<Object>(createdUser, HttpStatus.OK);
		
		
	}
	
}
