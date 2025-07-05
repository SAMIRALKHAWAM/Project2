package com.example.course_service.dto;

import com.example.course_service.model.CourseStatus;

import lombok.Data;

@Data
public class ShowCoursesByStatusRequest {


    private CourseStatus status;
}
