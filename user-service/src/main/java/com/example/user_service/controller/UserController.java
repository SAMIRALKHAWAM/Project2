package com.example.user_service.controller;

import org.springframework.web.bind.annotation.*;

import com.example.user_service.dto.AddTeacherRequest;
import com.example.user_service.handler.AccessDeniedException;
import com.example.user_service.model.User;

import com.example.user_service.service.AuthService;
import com.example.user_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/me")
    public String getCurrentUser(Authentication authentication) {
        return "You are authenticated as: " + authentication.getName();
    }

    @PostMapping("/add_teacher")
    public String addTeacher(@Valid @RequestBody AddTeacherRequest request, Authentication authentication) {
        String email = authentication.getName();

        User user = authService.findByEmail(email);
        return userService.AddTeacher(user, request);
    }

    @GetMapping("/teacher-id-by-email")
    public Long getTeacherIdByEmail(@RequestParam String email) {
        User user = authService.findByEmail(email);
        if (!user.getRole().equals("Teacher")) {
            throw new AccessDeniedException("Access Denied You not Teacher");
        }
        return user.getId();
    }


     @GetMapping("/admin-id-by-email")
    public Long getAdminIdByEmail(@RequestParam String email) {
        User user = authService.findByEmail(email);
        if (!user.getRole().equals("Admin")) {
            throw new AccessDeniedException("Access Denied You not Admin");
        }
        return user.getId();
    }

       @GetMapping("/student-id-by-email")
    public Long getStuduntIdByEmail(@RequestParam String email) {
        User user = authService.findByEmail(email);
        if (!user.getRole().equals("Student")) {
            throw new AccessDeniedException("Access Denied You not Student");
        }
        return user.getId();
    }


        @GetMapping("/test")
    public String gettest() {
       return "test";
    }
}
