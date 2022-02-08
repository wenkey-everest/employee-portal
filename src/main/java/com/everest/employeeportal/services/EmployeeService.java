package com.everest.employeeportal.services;

import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@AllArgsConstructor
public class EmployeeService{

    @PersistenceContext
    private EntityManager entityManager;

    private final EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long empId){
        if(employeeRepo.findById(empId).isPresent())
            return employeeRepo.findById(empId).get();
        throw new EmployeeNotFoundException("employee Id is not present");
    }

    public String createEmployee(Employee employee){
        employeeRepo.save(new Employee(employee.getEmpId(), employee.getName(), employee.getEverestEmailId()
        ,employee.getPassword(), employee.getPersonalEmailId(), employee.getDateOfBirth(), employee.getDateOfJoin()
        ,employee.getDesignation(),employee.getExperience(),employee.getBio(), employee.getPresentAddress(), employee.getPermanentAddress()));
        return "Inserted Successfully";
    }

    public String updateEmployee(Employee employee, Long empId){
        if(employeeRepo.findById(empId).isPresent()) {
            employeeRepo.save(new Employee(empId, employee.getName(), employee.getEverestEmailId()
                    , employee.getPassword(), employee.getPersonalEmailId(), employee.getDateOfBirth(), employee.getDateOfJoin()
                    , employee.getDesignation(), employee.getExperience(), employee.getBio(), employee.getPresentAddress(), employee.getPermanentAddress()));
            return "updated Successfully";
        }
        throw new EmployeeNotFoundException("employee Id is not present");
    }

    public String deleteEmployee(Long empId){
        List<Long> employeeId = new ArrayList<>();
        employeeId.add(empId);
        if(employeeRepo.findById(empId).isPresent()) {
            employeeRepo.deleteAllById(employeeId);
            return "deleted successfully";
        }
        throw new EmployeeNotFoundException("employee Id is not present");
    }

    public String truncateEmployeeAddress(){
        employeeRepo.truncateDate();
        return "Data is erased from the employee table";
    }

}
