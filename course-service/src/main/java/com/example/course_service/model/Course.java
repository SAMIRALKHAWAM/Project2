package com.example.course_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double price;

    private Long teacherId;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;
}
