package com.example.teamproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class User {

//  유저 아이디
  private Long id;

//  사용자 이름
  @NotBlank
  @Size(max=20)
  private String username;

//  이메일
  @NotBlank
  @Size(max=50)
  @Email
  private String email;

//  비밀번호
  @NotBlank
  @Size(max=120)
  private String password;

//  todo: 추가
  private String usernick;

//  todo : role column 추가 할것
//  todo : role 변수 추가 할것 테스트
//  DB 역할 컬럼
  private String role;

//  SpringSecuriry 역활
//  private Set<Role> roles = new HashSet<>();
  private Set<String> roles = new HashSet<>();

  public User(String usernick, String username, String email, String password) {
    this.usernick = usernick;
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
