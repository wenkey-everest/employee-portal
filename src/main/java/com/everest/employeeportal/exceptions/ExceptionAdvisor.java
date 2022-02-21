package com.everest.employeeportal.exceptions;


import com.everest.employeeportal.models.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeExceptionHandler(EmployeeNotFoundException ex, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), webRequest.getDescription(true));
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailIsRegisteredAlreadyException.class)
    public ResponseEntity<Object> emailIsRegisteredAlreadyExceptionhandler(EmailIsRegisteredAlreadyException ex, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders httpHeaders,
                                                                  HttpStatus httpStatus, WebRequest webRequest){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}
