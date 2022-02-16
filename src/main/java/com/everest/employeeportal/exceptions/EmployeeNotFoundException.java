package com.everest.employeeportal.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException() {
        super("Employee not found with Id");

    }
}
