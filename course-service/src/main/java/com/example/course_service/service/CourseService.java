package com.example.course_service.service;

import com.example.course_service.client.UserClient;
import com.example.course_service.dto.CreateCourseRequest;
import com.example.course_service.dto.UpdateCourseStatusRequest;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseStatus;
import com.example.course_service.repository.CourseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserClient userClient;

    public Course createCourse(CreateCourseRequest request,String teacherEmail) {

  Long teacherId = userClient.getTeacherIdByEmail(teacherEmail);
        
        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .teacherId(teacherId)
                .status(CourseStatus.PENDING)
                .build();

        return courseRepository.save(course);
    }

  
    public Course approveOrRejectCourse(Long id, UpdateCourseStatusRequest status,String adminEmail) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Long adminId = userClient.getAdminIdByEmail(adminEmail);
        
        course.setStatus(status.getStatus());
        return courseRepository.save(course);
    }

   @GetMapping
public List<Course> getAllCourses(@RequestParam(required = false) String status) {
    if (status != null) {
    
        CourseStatus courseStatus;
        try {
            courseStatus = CourseStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
        }
        return courseRepository.findByStatus(courseStatus);
    }
    return courseRepository.findAll();
}


 public Long getCourseIdIfExistAndApproved(Long courseId) {
       
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

        if (!course.getStatus().equals(CourseStatus.APPROVED)) {
            throw new IllegalStateException("Course status is not APPROVED");
        }

        return courseId;
    }

}
