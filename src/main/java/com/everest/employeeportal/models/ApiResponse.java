package com.everest.employeeportal.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    private String request;

    public ApiResponse(String message) {
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public ApiResponse(String message, String request) {
        this.message = message;
        this.time = LocalDateTime.now();
        this.request = request;
    }
}
