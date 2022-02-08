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
public class PermanentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long perId;

    @Column(name = "per_addressline1")
    private String addressLine1;

    @Column(name = "per_addressline2")
    private String addressLine2;

    @Column(name = "per_city")
    private String city;

    @Column(name = "per_state")
    private String state;

    @Column(name = "per_zipcode")
    private int zipCode;

    @Column(name = "per_country")
    private String country;

    @OneToOne(mappedBy = "permanentAddress")
    private Employee employee;

    public PermanentAddress() {
    }
}
