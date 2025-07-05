package com.example.course_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/teacher-id-by-email")
    Long getTeacherIdByEmail(@RequestParam String email);

    @GetMapping("/api/users/admin-id-by-email")
    Long getAdminIdByEmail(@RequestParam String email);
}
