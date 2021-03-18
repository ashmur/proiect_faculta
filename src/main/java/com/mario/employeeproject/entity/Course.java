package com.mario.employeeproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int numberOfHours;

    private int graduationDiploma;

    private int value;

    private int year;

    @ManyToOne
    private Employee employee;
}
