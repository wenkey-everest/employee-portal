package com.everest.employeeportal.exceptions;

public class EmailIsRegisteredAlreadyException extends RuntimeException {
    public EmailIsRegisteredAlreadyException() {
        super("Email is already registered");
    }
}
