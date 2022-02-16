package com.everest.employeeportal.repository;

import com.everest.employeeportal.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Query("select e from Employee e where firstName like %?1% or lastName like %?1%")
    List<Employee> findByName(String name);

}

