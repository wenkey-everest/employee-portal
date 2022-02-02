package com.everest.employeeportal.controller;

import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @GetMapping("/")
   public List<Employee> getAllEmployee(){
        return List.of();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable("empId") Long empId){
        return null;
    }
    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee){
        return null;
    }
    @PutMapping(name = "/{empId}")
    public Employee updateEmployee(@PathVariable("empId") Long empId ,Employee employee){
        return null;
    }

    @DeleteMapping("/{empId}")
    public Employee deleteEmployee(@PathVariable(name = "empId") Long empId){
        return null;
    }

}
