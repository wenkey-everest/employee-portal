package com.everest.employeeportal.controller;

import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/api/allEmployees")
    public Page<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/sortAllEmployees")
   public Page<Employee> sortAllEmployees(@RequestParam(name="sortByProperty1", required = false) String sortByProperty1,
                                        @RequestParam(name="sortByProperty2", required = false) Optional<String> sortByProperty2 ){
        if(sortByProperty2.isPresent()){
            return employeeService.getSortBy(sortByProperty1,sortByProperty2.get());
        }
        return employeeService.getSortBy(sortByProperty1);
    }

    @GetMapping("/api/getEmployeeBy/{empId}")
    public Employee getEmployeeById(@PathVariable("empId") Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping("/api/CreateEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/api/UpdateEmployeeBy/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable("empId") Long empId , @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee, empId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/deleteEmployee/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("empId") Long empId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(empId), HttpStatus.OK);
    }

    @DeleteMapping("/api/deleteAllEmployees")
    public ResponseEntity<String> deleteEmployee(){
        return  new ResponseEntity<>(employeeService.truncateEmployeeAddress(), HttpStatus.OK);
    }

    @GetMapping("/api/SearchEmployee")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return new ResponseEntity<>(employeeService.searchByName(firstName, lastName), HttpStatus.OK);
    }

}
