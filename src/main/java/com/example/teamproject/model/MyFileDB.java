package com.example.teamproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MyFileDB {

  private String id;
  private String username;

  private String name;
//  todo: String title, String content,
  private String title;

  private String content;

  private String type;

//  BLOB 데이터형으로 저장
  private byte[] data;
//  TODO 추가
  private String deleteYn;

  private String insertTime;

  private String deleteTime;
  private String usernick;


  //  todo: String title, String content,
  public MyFileDB(String name, String title, String content, String type, byte[] data, String username) {
    this.name = name;
    this.title = title;
    this.content = content;
    this.type = type;
    this.data = data;
    this.username = username;

  }
}
