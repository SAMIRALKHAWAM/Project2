package com.example.course_service.repository;

import com.example.course_service.model.Course;
import com.example.course_service.model.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStatus(CourseStatus status);
}
