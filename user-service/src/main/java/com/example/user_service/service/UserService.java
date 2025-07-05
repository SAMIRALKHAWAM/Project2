package com.example.user_service.service;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.AddTeacherRequest;

import com.example.user_service.handler.AccessDeniedException;
import com.example.user_service.handler.EmailAlreadyExistsException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

private final UserRepository userRepository;
private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


 public String AddTeacher(User user,AddTeacherRequest request) {
    if (!user.getRole().equals("Admin")) {
            throw new AccessDeniedException("Not Allow To Add Teacher , You Not Admin");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "Done Teacher Create Successfully";
    }

}
