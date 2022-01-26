package com.hassnain.userservice.dto;

import com.hassnain.userservice.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;

}
