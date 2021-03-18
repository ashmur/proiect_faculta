package com.mario.employeeproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mario.employeeproject.repository.CourseRepository;

@RestController
@CrossOrigin
@RequestMapping("api/")
@AllArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;
}
