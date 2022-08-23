package com.example.teamproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Profile {

  private String id;

  private String username;
  private String name;
//  todo: String title, String content,

  private String type;

//  BLOB 데이터형으로 저장
  private byte[] data;

  private String usernick;

  private String insertTime;

  private String deleteTime;

  private String deleteYn;

  private String updateTime;



//  todo: String title, String content,

  public Profile(String name, String type, byte[] data, String username, String usernick) {
    this.name = name;
    this.type = type;
    this.data = data;
    this.username = username;
    this.usernick = usernick;
  }
}
