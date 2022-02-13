package com.everest.employeeportal.repository;

import com.everest.employeeportal.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "Select * from employee_details where first_name like ?1% and last_name like ?2%",nativeQuery = true)
    List<Employee> findByName(String firstName, String lastName);
}

