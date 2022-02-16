package com.everest.employeeportal.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("present")
public class Present extends Address {

}
