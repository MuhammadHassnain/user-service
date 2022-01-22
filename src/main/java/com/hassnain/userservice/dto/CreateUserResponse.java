package com.hassnain.userservice.dto;

import com.hassnain.userservice.entity.UserType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CreateUserResponse {


    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
}
