package com.everest.employeeportal.exceptions;

public class RequiredAllParamException extends RuntimeException {
    public RequiredAllParamException() {
        super("All parameters are required in form");
    }
}
