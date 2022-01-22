package com.hassnain.userservice.service;

import com.hassnain.userservice.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	
	
	User createUser(User user);
	User updateUser(User user);

	
}
