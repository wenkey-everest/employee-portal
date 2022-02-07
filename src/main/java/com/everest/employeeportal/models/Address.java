package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "permanent_address")
@SecondaryTable(name = "present_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addId;
    @Column(name = "addressLine1")
    private String addressLine1;
    @Column(name = "addressLine2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zipCode")
    private String zipCode;
    @Column(name = "country")
    private String country;

//    @OneToOne(mappedBy = "presentAddress")
//    private Employee employee;
//
//    @OneToOne(mappedBy = "permanentAddress")
//    private Employee employee1;

    public Address() {
    }
}
