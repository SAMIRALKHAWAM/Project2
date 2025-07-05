package com.example.user_service.handler;

public class AccessDeniedException extends RuntimeException{
public AccessDeniedException(String message) {
        super(message);
    }
}

