package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class Employee {
    private Long empID;
    private String name;
    private String everestEmailId;
    private String password;
    private String personalEmailId;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
    private String designation;
    private int experience;
    private String bio;
    private Address presentAddress;
    private Address permanentAddress;

}
