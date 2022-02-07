package com.everest.employeeportal.controller;

import com.everest.employeeportal.models.Address;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @GetMapping("/")
   public List<Employee> getAllEmployee(){
        return employeeRepo.getAllEmployee();
    }

    @GetMapping("/{empId}")
    public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("empId") Long empId){
        return new ResponseEntity<List<Employee>>(employeeRepo.getEmployeeById(empId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<String>(employeeRepo.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable("empId") Long empId ,@RequestBody Employee employee){
        return new ResponseEntity<String>(employeeRepo.updateEmployee(empId, employee), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "empId") Long empId){
        return new ResponseEntity<String>(employeeRepo.deleteEmployee(empId), HttpStatus.OK) ;
    }

}
