package com.example.teamproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin("http://192.168.0.166:8080")
// TODO : @CrossOrigin("http://192.168.0.166:8080")
@RestController
@RequestMapping("/api/auth")
public class AdminController {
//  공개 엑세스용
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

//  사용자용 ROLE_USER 또는 ROLE_MODERATOR 또는 ROLE_ADMIN
  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

//  ROLE_MODERATOR
  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

//  ROLE_ADMIN
  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}