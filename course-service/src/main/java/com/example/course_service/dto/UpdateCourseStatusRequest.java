package com.example.course_service.dto;

import com.example.course_service.model.CourseStatus;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCourseStatusRequest {

     @NotNull(message = "status is required")
    private CourseStatus status;
}
