package com.sharetaxi.userservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharetaxi.userservice.entity.User;
import com.sharetaxi.userservice.exception.UserException;
import com.sharetaxi.userservice.repository.UserRepository;
import com.sharetaxi.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User createUser(User user) {

		// TODO Auto-generated method stub

		Optional<User> dbUserO = userRepo.findByEmail(user.getEmail());
		if (!dbUserO.isPresent()) {
			return userRepo.save(user);
		}

		throw new UserException.DuplicateEmailException(user.getEmail());

	}


	@Override
	public User updateUser(User user) {
		Optional<User> dbUserO = userRepo.findById(user.getId());
		if (dbUserO.isPresent()) {
			return userRepo.save(user);
		}

		throw new UserException.UserNotFoundException(user.getEmail());

	}

}
