package com.everest.employeeportal.services;

import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll(Sort.by(Sort.Direction.ASC, "firstName").and(Sort.by(Sort.Direction.ASC,"dateOfJoin")));
    }

    public Employee getEmployeeById(Long empId){
        if(employeeRepo.findById(empId).isPresent())
            return employeeRepo.findById(empId).get();
        throw new EmployeeNotFoundException();
    }

    public String createEmployee(Employee employee){
        employeeRepo.save(employee);
        return "Inserted Successfully";
    }

    public String updateEmployee(Employee employee, Long empId){
        if(employeeRepo.findById(empId).isPresent()) {
            employeeRepo.save(new Employee(empId, employee.getFirstName(),employee.getLastName(), employee.getEverestEmailId()
                    , employee.getPassword(), employee.getPersonalEmailId(), employee.getDateOfBirth(), employee.getDateOfJoin()
                    , employee.getDesignation(), employee.getExperience(), employee.getBio(), employee.getPresentAddress(), employee.getPermanentAddress()));
            return "updated Successfully";
        }
        throw new EmployeeNotFoundException();
    }

    public String deleteEmployee(Long empId){
        List<Long> employeeId = new ArrayList<>();
        employeeId.add(empId);
        if(employeeRepo.findById(empId).isPresent()) {
            employeeRepo.deleteAllById(employeeId);
            return "deleted successfully";
        }
        throw new EmployeeNotFoundException();
    }

    public String truncateEmployeeAddress(){
        employeeRepo.deleteAll();
        return "Data is erased from the employee table";
    }

    public List<Employee> searchByName(String firstName, String lastName){

        return employeeRepo.findByName(firstName, lastName);
    }

}
