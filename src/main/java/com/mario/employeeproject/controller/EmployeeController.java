package com.mario.employeeproject.controller;

import com.mario.employeeproject.commonDefines.Response;
import com.mario.employeeproject.entity.Course;
import com.mario.employeeproject.entity.Employee;
import com.mario.employeeproject.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.mario.employeeproject.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;

    @PutMapping("employee")
    public Response<Employee> createEmployee(@RequestBody Employee createEmployeeRequest) {
        if (createEmployeeRequest.getId() != null)
            throw new RuntimeException("New employee can't have an ID!");

        return new Response<>(employeeRepository.save(createEmployeeRequest), "Employee created!");
    }

    @PostMapping("employee")
    public Response<Employee> modifyEmployee(@RequestBody Employee modifyEmployeeRequest) {
        Optional<Employee> employeeOptional = employeeRepository.findById(modifyEmployeeRequest.getId());
        if(employeeOptional.isEmpty())
            throw new RuntimeException("Employee not found!");

        Employee employee = employeeOptional.get();
        employee.setAllParameters(modifyEmployeeRequest);

        return new Response<>(employeeRepository.save(employee), "Employee modified!");
    }

    @GetMapping("employee/{id}")
    public Response<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty())
            throw new RuntimeException("Employee not found!");

        return new Response<>(employeeOptional.get(), "Employee found!");
    }

    @GetMapping("employee/{id}")
    public Response<List<Employee>> getEmployeeByNameLike(@RequestBody String employeeName) {
        String regex = "%" + employeeName + "%";
        List<Employee> employeeList = employeeRepository.findAllByNameLikeOrderById(regex);

        return new Response<>(employeeList, "Employees found!");
    }

    @DeleteMapping("employee/{id}")
    public Response<Boolean> deleteEmployeeById(@PathVariable("id") long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty())
            throw new RuntimeException("Employee not found!");

        Employee employee = employeeOptional.get();
        List<Course> courseList = employee.getCourseList();
        for(Course course : courseList)
            courseRepository.delete(course);

        employeeRepository.delete(employee);

        return new Response<>(true, "Employee deleted successfully!");
    }
}
