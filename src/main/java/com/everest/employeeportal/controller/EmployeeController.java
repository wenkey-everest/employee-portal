package com.everest.employeeportal.controller;

import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepo;
import com.everest.employeeportal.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/")
   public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable("empId") Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping("/")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable("empId") Long empId , @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee, empId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "empId") Long empId){
        return new ResponseEntity<>(employeeService.deleteEmployee(empId),HttpStatus.OK) ;
    }

}
