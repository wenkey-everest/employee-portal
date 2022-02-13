package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_details")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;


    @NotBlank(message = "first name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "email is mandatory")
    @Column(name = "everest_email_id")
    private String everestEmailId;

    @NotBlank(message = "password is mandatory")
    @Column(name = "password")
    private String password;

    @Column(name = "personal_email_id")
    private String personalEmailId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join")
    private LocalDate dateOfJoin;

    @Column(name = "designation")
    private String designation;

    @Column(name = "experience")
    private int experience;

    @Column(name = "bio")
    private String bio;

    @NotBlank(message = "present address is mandatory")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address", referencedColumnName = "present_id")
    private PresentAddress presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address", referencedColumnName = "permanent_id")
    private PermanentAddress permanentAddress;

}
