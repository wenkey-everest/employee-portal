package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String AddressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}
