package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "everest_email_id", nullable = false)
    private String everestEmailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "personal_email_id", nullable = false)
    private String personalEmailId;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join", nullable = false)
    private LocalDate dateOfJoin;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "experience", nullable = false)
    private int experience;

    @Column(name = "bio", nullable = false)
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address", referencedColumnName = "id", nullable = false)
    private Present presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address", referencedColumnName = "id", nullable = false)
    private Permanent permanentAddress;

}
