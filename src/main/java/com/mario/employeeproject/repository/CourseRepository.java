package com.mario.employeeproject.repository;

import com.mario.employeeproject.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
}
