package com.everest.employeeportal.repository;

import com.everest.employeeportal.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from employee_details", nativeQuery = true)
    void truncateDate();
}

