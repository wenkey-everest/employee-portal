package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name = "Employee_details")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;

    @Column(name = "name")
    private String name;

    @Column(name = "everestemailid")
    private String everestEmailId;

    @Column(name = "password")
    private String password;

    @Column(name = "personalemailid")
    private String personalEmailId;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name = "dateofjoin")
    private LocalDate dateOfJoin;

    @Column(name = "designation")
    private String designation;

    @Column(name = "experience")
    private int experience;

    @Column(name = "bio")
    private String bio;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "addId", referencedColumnName = "empId")
//    private Address presentAddress;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "addId", referencedColumnName = "empId")
//    private Address permanentAddress;

    public Employee() {
    }
}
