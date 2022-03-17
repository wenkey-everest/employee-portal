package com.everest.employeeportal.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/user")
    //@PreAuthorize("hasRole('ROLE_USER')")
    @PreAuthorize("isAuthenticated()")
    public String user() {
        return "user";
    }



}
