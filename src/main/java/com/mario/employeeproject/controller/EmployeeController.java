package com.mario.employeeproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mario.employeeproject.repository.EmployeeRepository;

@RestController
@CrossOrigin
@RequestMapping("api/")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
}
