package com.example.user_service.handler;

public class EmailOrPasswordWrongException  extends RuntimeException{
  public EmailOrPasswordWrongException(String message) {
        super(message);
    }
}
