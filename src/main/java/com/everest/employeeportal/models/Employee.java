package com.everest.employeeportal.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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

    @NotBlank(message = "firstName should not be empty")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "everest_email_id")
    @Email(message = "Email should end with everest.engineering", regexp = "^(.+)@everest.engineering$")
    @NotBlank(message = "Everest email is required")
    private String everestEmailId;

    @NotEmpty
    @Size(min = 8, message = "password need to be 8 char")
    @Column(name = "password")
    private String password;

    @Email(message = "email need to be valid", regexp = "^(.+)@(.+)$")
    @NotBlank(message = "Personal email should be needed")
    @Column(name = "personal_email_id")
    private String personalEmailId;

    @Column(name = "date_of_birth")
    @NotBlank(message = "Date of birth is needed")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join")
    @NotBlank(message = "Date of join is  needed")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoin;

    @Column(name = "designation")
    @NotBlank(message = "designation is needed")
    private String designation;

    @Column(name = "experience")
    private int experience;

    @Column(name = "bio")
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank(message = "present address is needed")
    @JoinColumn(name = "present_address", referencedColumnName = "id")
    private Present presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address", referencedColumnName = "id")
    private Permanent permanentAddress;

}
