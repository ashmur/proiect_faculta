package com.mario.employeeproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String firm;

    private String position;

    private Date date_of_employment;

    @OneToMany
    List<Course> courseList;

    public void setAllParameters(Employee modifyEmployeeRequest) {
        name = modifyEmployeeRequest.getName();
        firm = modifyEmployeeRequest.getFirm();
        position = modifyEmployeeRequest.getPosition();
        date_of_employment = modifyEmployeeRequest.getDate_of_employment();
    }
}
