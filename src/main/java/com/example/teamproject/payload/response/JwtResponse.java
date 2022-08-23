package com.example.teamproject.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;

  private String usernick;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String usernick, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.usernick = usernick;
    this.email = email;
    this.roles = roles;
  }
}
