package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "present_address")
public class PresentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long present_id;
    @Column(name = "pre_addressline1")
    private String addressLine1;

    @Column(name = "pre_addressline2")
    private String addressLine2;

    @Column(name = "pre_city")
    private String city;

    @Column(name = "pre_state")
    private String state;

    @Column(name = "pre_zipcode")
    private int zipCode;

    @Column(name = "pre_country")
    private String country;

}
