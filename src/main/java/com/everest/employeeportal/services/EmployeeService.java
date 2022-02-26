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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Optional<Employee> getEmployeeById(Long empId){
        return employeeRepository.findById(empId);
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
        throw new EmployeeNotFoundException(empId);
    }

    public void deleteEmployee(Long empId){
        employeeRepository.deleteById(empId);
    }

    public void truncateEmployeeDetails(){
        employeeRepository.deleteAll();
    }

    public Page<Employee> searchEmployeeByName(String name, Pageable pageable){
        return employeeRepository.findByName(name, pageable);
    }
}
