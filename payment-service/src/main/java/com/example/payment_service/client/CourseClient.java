package com.example.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "course-service")
public interface CourseClient {

        @GetMapping("/api/courses/course-id-by-course")
    Long getCourseIdIfExistAndApproved(@RequestParam Long id);
}
