package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
@Setter
=======
import javax.persistence.*;

>>>>>>> WIP-JPA
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
@Inheritance
@DiscriminatorColumn(name = "address_type", discriminatorType = DiscriminatorType.STRING)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line_1")
    private String addressLine1;
<<<<<<< HEAD
    private String AddressLine2;
=======

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
>>>>>>> WIP-JPA
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private int zipCode;

    @Column(name = "country")
    private String country;


}

