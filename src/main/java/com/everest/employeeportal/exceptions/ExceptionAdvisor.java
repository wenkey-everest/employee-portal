package com.everest.employeeportal.exceptions;


import com.everest.employeeportal.models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeExceptionHandler(EmployeeNotFoundException ex, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), webRequest.getDescription(true));
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RequiredAllParamException.class)
    public ResponseEntity<Object> employeeExceptionHandler(RequiredAllParamException ex, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailIsRegisteredAlreadyException.class)
    public ResponseEntity<Object> employeeExceptionHandler(EmailIsRegisteredAlreadyException ex, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
