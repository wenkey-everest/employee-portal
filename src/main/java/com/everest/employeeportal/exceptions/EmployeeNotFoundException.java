package com.everest.employeeportal.exceptions;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long empId) {
        super("Employee not found with Id "+empId);

    }
}
