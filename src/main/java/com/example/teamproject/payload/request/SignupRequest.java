package com.example.teamproject.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

//  private Set<String> roles;
  private String role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  //  todo: 추가
  private String usernick;

}
