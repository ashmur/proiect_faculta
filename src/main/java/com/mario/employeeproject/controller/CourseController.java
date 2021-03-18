package com.mario.employeeproject.controller;

import com.mario.employeeproject.commonDefines.Response;
import com.mario.employeeproject.entity.Course;
import com.mario.employeeproject.entity.Employee;
import com.mario.employeeproject.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.mario.employeeproject.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/")
@AllArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;

    @PutMapping("course")
    public Response<Course> createCourse(@RequestBody Course createCourseRequest) {
        if (createCourseRequest.getId() != null)
            throw new RuntimeException("New course can't have an ID!");

        Optional<Employee> employeeOptional = employeeRepository.findById(createCourseRequest.getEmployee().getId());
        if (employeeOptional.isEmpty())
            throw new RuntimeException("New course must be bound to an employee!");

        Employee employee = employeeOptional.get();
        List<Course> employeeCourses = employee.getCourseList();
        employeeCourses.add(createCourseRequest);

        createCourseRequest = courseRepository.save(createCourseRequest);
        employeeRepository.save(employee);

        return new Response<>(createCourseRequest, "Course created!");
    }

    @PostMapping("course")
    public Response<Course> modifyCourse(@RequestBody Course modifyCourseRequest) {
        Optional<Course> courseOptional = courseRepository.findById(modifyCourseRequest.getId());
        if(courseOptional.isEmpty())
            throw new RuntimeException("Invalid course!");

        Course course = courseOptional.get();
        course.setAllParameters(modifyCourseRequest);

        return new Response<>(courseRepository.save(course), "Course modified!");
    }

    @GetMapping("course/{id}")
    public Response<Course> getCourseById(@PathVariable("id") long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isEmpty())
            throw new RuntimeException("Course not found!");

        return new Response<>(courseOptional.get(), "Course found!");
    }

    @GetMapping("course")
    public Response<List<Course>> getCoursesByNameLike(@RequestBody String courseName) {
        String regex = "%" + courseName + "%";
        List<Course> courseList = courseRepository.findAllByNameLikeOrderByIdAsc(regex);

        return new Response<>(courseList, "Courses found!");
    }

    @GetMapping("course/employee/{id}")
    public Response<Course> getCourseByEmployeeId(@PathVariable("id") long employeeId) {
        Optional<Course> courseOptional = courseRepository.findByEmployee_Id(employeeId);
        if(courseOptional.isEmpty())
            throw new RuntimeException("Course not found!");

        return new Response<>(courseOptional.get(), "Course found!");
    }

    @DeleteMapping("course/{id}")
    public Response<Boolean> deleteCourse(@PathVariable("id") long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isEmpty())
            throw new RuntimeException("Course not found!");

        Optional<Employee> employeeOptional = employeeRepository.findById(courseOptional.get().getEmployee().getId());
        if(employeeOptional.isEmpty())
            throw new RuntimeException("Employee not found!");

        Employee employee = employeeOptional.get();
        Course course = courseOptional.get();
        List<Course> courseList = employee.getCourseList();
        courseList.remove(course);

        employeeRepository.save(employee);
        courseRepository.deleteById(courseId);

        return new Response<>(true, "Course deleted!");
    }
}
