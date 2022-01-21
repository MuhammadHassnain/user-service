package com.hassnain.userservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hassnain.userservice.entity.User;
import com.hassnain.userservice.entity.UserType;
import com.hassnain.userservice.service.UserService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{

	
//	private static final Logger logger= LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
//	
//	@Autowired UserService userService;
	
	@Override
	public void run(String...args) throws Exception{
//		logger.info(args.toString());
//		
//		User user = new User(null, "Muhammad Hassnain", "Barkat", "hassnainkadhar@gmail.com", "password", UserType.ADMIN);
//		
//		userService.createUser(user,"Hassnain");
//		User user1 = new User(null, "Muhammad Hassnain", "Barkat", "hassnainkadhar1@gmail.com", "password", UserType.NORMAL);
//		
//		
//		
//		user1 = userService.createUser(user1,"Barkat");
//		
//		user1.setFirstName("Hassnain");
//		
//		user1.setUpdatedBy("abc");
//		userService.updateUser(user1);
		
		
		
	}
	
}
