package com.everest.employeeportal.repository;

import com.everest.employeeportal.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepo {
   private static Map<String, String>  map = new HashMap<>();

   List<Employee> getAllEmployee(){
      return null;
   }

   Employee getEmployeeById(){
      return null;
   }

   Employee createEmployee(Employee employee){
      return null;
   }

   Employee updateEmployee(Employee employee){
      return null;
   }

   Employee deleteEmployee(Long empId){
      return null;
   }

}
