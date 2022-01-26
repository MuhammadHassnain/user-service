package com.hassnain.userservice.service.impl;

import java.text.MessageFormat;
import java.util.Optional;

import com.hassnain.userservice.exception.user.UserExceptionType;
import com.hassnain.userservice.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hassnain.userservice.entity.User;
import com.hassnain.userservice.exception.user.UserException;
import com.hassnain.userservice.repository.UserRepository;
import com.hassnain.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepo, UserMapper userMapper) {
		this.userRepo = userRepo;
		this.userMapper = userMapper;
	}


	/**
	 *
	 * */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> userDbOptional = userRepo.findByEmail(email);
		if(userDbOptional.isPresent()){
			User user = userDbOptional.get();

			return userMapper.UserToUserDetail(user);
		}
		throw new UsernameNotFoundException(MessageFormat.format(UserExceptionType.USER_NOT_FOUND.getMessage(),email));
	}

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


	@Override
	public User findUserByEmail(String email) {
		Optional<User> dbUserO = userRepo.findByEmail(email);
		if (dbUserO.isPresent()) {
			return dbUserO.get();
		}
		throw new UserException.UserNotFoundException(email);
	}
}
