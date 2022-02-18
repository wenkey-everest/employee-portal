package com.everest.employeeportal.services;

import com.everest.employeeportal.exceptions.EmailIsRegisteredAlreadyException;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long empId){
        return employeeRepository.findById(empId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee createEmployee(Employee employee){
        if(employeeRepository.existsByEverestEmailId(employee.getEverestEmailId())){
            throw new EmailIsRegisteredAlreadyException();
        }
       return employeeRepository.save(employee);

    }

    public Employee updateEmployee(Employee employee, Long empId){
        if(employeeRepository.existsById(empId)) {
            employee.setEmpId(empId);
            return employeeRepository.save(employee);
        }
        throw new EmployeeNotFoundException();
    }

    public String deleteEmployee(Long empId){
            employeeRepository.deleteById(empId);
            return "deleted successfully";
    }

    public String truncateEmployeeDetails(){
        employeeRepository.deleteAll();
        return "Data is erased from the employee table";
    }

    public Page<Employee> searchEmployeeByName(String name, Pageable pageable){
        return employeeRepository.findByName(name, pageable);
    }
}
