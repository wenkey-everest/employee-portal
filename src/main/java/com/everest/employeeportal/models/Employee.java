package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee_details")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presentaddress", referencedColumnName = "preId")
    private PresentAddress presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanentaddress", referencedColumnName = "perId")
    private PermanentAddress permanentAddress;

}
