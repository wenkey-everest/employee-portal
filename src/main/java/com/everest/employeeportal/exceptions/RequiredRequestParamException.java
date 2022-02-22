package com.everest.employeeportal.exceptions;

public class RequiredRequestParamException extends RuntimeException {
    public RequiredRequestParamException() {
        super("name is missing");
    }
}
