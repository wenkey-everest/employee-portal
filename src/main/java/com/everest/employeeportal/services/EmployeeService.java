package com.everest.employeeportal.services;

import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees() {
        return employeeRepository.findAll(PageRequest.of(0,20));
    }

    public Employee getEmployeeById(Long empId){
        if(employeeRepository.findById(empId).isPresent())
            return employeeRepository.findById(empId).get();
        throw new EmployeeNotFoundException();
    }

    public String createEmployee(Employee employee){
        employeeRepository.save(employee);
        return "Inserted Successfully";
    }

    public String updateEmployee(Employee employee, Long empId){
        if(employeeRepository.findById(empId).isPresent()) {
            employeeRepository.save(new Employee(empId, employee.getFirstName(),employee.getLastName(), employee.getEverestEmailId()
                    , employee.getPassword(), employee.getPersonalEmailId(), employee.getDateOfBirth(), employee.getDateOfJoin()
                    , employee.getDesignation(), employee.getExperience(), employee.getBio(), employee.getPresentAddress(), employee.getPermanentAddress()));
            return "updated Successfully";
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


    public Page<Employee> getSortBy(String sortProperty){
        String[] propSplit = sortProperty.split(":");
        Sort sortByProperty =Sort.by(Sort.Direction.valueOf(propSplit[1].toUpperCase()), propSplit[0]);
        return employeeRepository.findAll(PageRequest.of(0, 20, sortByProperty));
    }

    public Page<Employee> getSortBy(String firstNameProp, String dateOfJoin) {
        String[] firstNameSplit = firstNameProp.split(":");
        String[] dateOfJoinSplit = dateOfJoin.split(":");

        Sort sortByProperty1 = Sort.by(Sort.Direction.valueOf(firstNameSplit[1].toUpperCase()), firstNameSplit[0]);
        Sort sortByProperty2 = Sort.by(Sort.Direction.valueOf(dateOfJoinSplit[1].toUpperCase()), dateOfJoinSplit[0]);
        return employeeRepository.findAll(PageRequest.of(0, 20, sortByProperty1.and(sortByProperty2)));
        }

    public List<Employee> searchEmployeeByName(String name){
        return employeeRepository.findByName(name);
    }
}
