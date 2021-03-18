package com.mario.employeeproject.repository;

import com.mario.employeeproject.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findAllByNameLikeOrderByIdAsc(@Param("name") String name);
    Optional<Course> findByEmployee_Id(@Param("id") Long id);
}
