package com.sharetaxi.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aiksath.core.entity.AbstractTimestampEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name ="users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends AbstractTimestampEntity{
	
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	@Column(name="FIRST_NAME",nullable = false)
	private String firstName;

	@Column(name="LAST_NAME",nullable = false)
	private String lastName;

	@Column(name="EMAIL",nullable = false,unique = true)
	private String email;
	
	@Column(name="PASSWORD",nullable = false)
	private String password;
	
	@Column(name="USER_TYPE",nullable=false)
	private UserType userType;
	
	
	

}
