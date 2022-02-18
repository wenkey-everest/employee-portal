package com.everest.employeeportal.controller;

import com.everest.employeeportal.exceptions.RequiredAllParamException;
import com.everest.employeeportal.models.ApiResponse;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.models.ResultPage;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
   public ResultPage getAllEmployees(Pageable pageable){
        Page<Employee> page = employeeService.getAllEmployees(pageable);
        return new ResultPage(page);

    }
    @GetMapping("/{empId}")
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
    public ResponseEntity<Object> deleteEmployee(@PathVariable("empId") Long empId) {
        ApiResponse apiResponse = new ApiResponse(employeeService.deleteEmployee(empId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteAllEmployees(){
        ApiResponse apiResponse = new ApiResponse(employeeService.truncateEmployeeDetails());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResultPage searchEmployee(@RequestParam(value = "name") String name, Pageable pageable){
        Page<Employee> employeePage= employeeService.searchEmployeeByName(name, pageable);
        return new ResultPage(employeePage);
    }

}
