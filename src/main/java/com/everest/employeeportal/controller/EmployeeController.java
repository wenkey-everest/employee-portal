package com.everest.employeeportal.controller;

import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.exceptions.RequiredAllParamException;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
   public Page<Employee> sortAllEmployees(@RequestParam(name="sortByProperty1", required = false) String sortByProperty1,
                                        @RequestParam(name="sortByProperty2", required = false) String sortByProperty2 ){
        if(sortByProperty2!=null){
            return employeeService.getSortBy(sortByProperty1,sortByProperty2);
        }if(sortByProperty1!= null) {
            return employeeService.getSortBy(sortByProperty1);
        }
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable("empId") Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping("")
    public ResponseEntity<String> createEmployee(@RequestBody @Validated Employee employee, BindingResult result){
        if(result.hasErrors()){
            throw new RequiredAllParamException();
        }
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable("empId") Long empId , @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee, empId), HttpStatus.ACCEPTED);
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
    public List<Employee> searchEmployee(@RequestParam("name") String name){
        return employeeService.searchEmployeeByName(name);
    }

}
