package com.example.teamproject.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class LoginRequest {

    //  todo: 추가
    private String usernick;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
