package com.everest.employeeportal.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long empId) {
        super("Employee not found with Id "+empId);

    }
}
