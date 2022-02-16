package com.everest.employeeportal.controller;

import com.everest.employeeportal.exceptions.RequiredAllParamException;
import com.everest.employeeportal.models.Employee;
<<<<<<< HEAD
import com.everest.employeeportal.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
>>>>>>> WIP-JPA
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

<<<<<<< HEAD
    @GetMapping("/")
   public List<Employee> getAllEmployee(){
        return employeeRepo.getAllEmployee();
=======
    @GetMapping("")
   public Page<Employee> getAllEmployees(Pageable pageable){
        return employeeService.getAllEmployees(pageable);
>>>>>>> WIP-JPA
    }
    @GetMapping("/{empId}")
<<<<<<< HEAD
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") Long empId){
        return new ResponseEntity<Employee>(employeeRepo.getEmployeeById(empId), HttpStatus.OK);
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
=======
    public Employee getEmployeeById(@PathVariable("empId") Long empId){
            return employeeService.getEmployeeById(empId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Validated Employee employee, BindingResult result){
        if(result.hasErrors()){
            throw new RequiredAllParamException();
        }
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("empId") Long empId , @RequestBody @Validated Employee employee,
                                   BindingResult result){
        if(result.hasErrors()){
            throw new RequiredAllParamException();
        }
        return employeeService.updateEmployee(employee, empId);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("empId") Long empId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(empId), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllEmployees(){
        return  new ResponseEntity<>(employeeService.truncateEmployeeDetails(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public Page<Employee> searchEmployee(@RequestParam("name") String name, Pageable pageable){
        return employeeService.searchEmployeeByName(name, pageable);
>>>>>>> WIP-JPA
    }

}
