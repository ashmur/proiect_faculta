package com.mario.employeeproject.repository;

import com.mario.employeeproject.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
    List<Employee> findAllByNameLikeOrderById(@Param("name") String name);
}
