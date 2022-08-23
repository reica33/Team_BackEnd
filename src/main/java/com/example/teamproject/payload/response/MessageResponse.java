package com.example.teamproject.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageResponse {
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }
}
