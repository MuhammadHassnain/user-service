package com.hassnain.userservice.config;

import com.hassnain.core.security.jwt.JwtConfiguration;
import com.hassnain.core.security.jwt.JwtManager;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BasicConfiguration {


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	JwtManager jwtManager(){return new JwtManager();}


	@Bean
	JwtConfiguration jwtConfiguration(){return new JwtConfiguration();}
}
