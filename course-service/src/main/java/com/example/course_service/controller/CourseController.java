package com.example.course_service.controller;

import com.example.course_service.dto.CreateCourseRequest;
import com.example.course_service.dto.UpdateCourseStatusRequest;
import com.example.course_service.model.Course;

import com.example.course_service.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add_course")
    public Course create(@Valid @RequestBody CreateCourseRequest request,Authentication authentication) {
        String email = authentication.getName();
        return courseService.createCourse(request,email);
    }


    @PutMapping("/{id}/status")
    public Course updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateCourseStatusRequest status,Authentication authentication) {
         String email = authentication.getName();
        return courseService.approveOrRejectCourse(id, status,email);
    }

    @GetMapping
    public List<Course> getAllCourses(@RequestParam(required = false) String status) {
        return courseService.getAllCourses(status);
    }

      @GetMapping("/course-id-by-course")
    public Long getCourseIdIfExistAndApproved(@RequestParam Long id) {
       return courseService.getCourseIdIfExistAndApproved(id);
    }
}
 