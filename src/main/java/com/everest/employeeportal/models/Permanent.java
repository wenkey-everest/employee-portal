package com.everest.employeeportal.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("permanent")
public class Permanent extends Address {
    
}
