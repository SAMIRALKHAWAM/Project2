package com.example.user_service.dto;


import com.example.user_service.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserResponse {

 
    private User user;
}
