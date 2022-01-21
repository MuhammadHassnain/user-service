package com.hassnain.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hassnain.userservice.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	Optional<User> findByEmail(String email);
	Optional<User> findById(Integer id);

	

}
