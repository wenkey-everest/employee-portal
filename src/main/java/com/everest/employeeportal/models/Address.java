package com.everest.employeeportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private Optional<String> getAddressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}
