package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "details")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @NotEmpty(message = "firstName should not be empty")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "everest_email_id")
    @Email(message = "Email cannot be empty")
    private String everestEmailId;

    @NotEmpty(message = "password need to be empty")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "email id is required")
    @Column(name = "personal_email_id")
    private String personalEmailId;

    @NotEmpty(message = "date of birth required")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join")
    private LocalDate dateOfJoin;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "experience")
    private int experience;

    @Column(name = "bio", nullable = false)
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @NotEmpty(message = "present Id is needed")
    @JoinColumn(name = "present_address", referencedColumnName = "id")
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address", referencedColumnName = "id")
    private Address permanentAddress;

}
