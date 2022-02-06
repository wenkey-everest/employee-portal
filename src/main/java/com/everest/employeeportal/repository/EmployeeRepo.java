package com.everest.employeeportal.repository;

import com.everest.employeeportal.models.Address;
import com.everest.employeeportal.models.Employee;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepo {
   private static final Map<Long, Employee>  employeeMap = new HashMap<>();

   private final AtomicLong autoId = new AtomicLong();

   public List<Employee> getAllEmployee(){
      return employeeMap.values().stream().toList();
   }

   public Employee getEmployeeById(Long empID) {
         return employeeMap.get(empID);
   }

   public String createEmployee(Employee employee){
         employeeMap.put(autoId.incrementAndGet(), employee);
         employee.setEmpID(autoId.get());
         return "Inserted successfully";
   }

   public String updateEmployee(Long empID, Employee employee){
         if(employeeMap.containsKey(empID)){
            employee.setEmpID(empID);
            employeeMap.put(empID, employee);
            return "updated successfully";
         }
         throw new RuntimeException("employee id not in the database");
   }

  public String deleteEmployee(Long empId){
      try {
          employeeMap.remove(empId);
         return "Deleted Successfully";
      }catch (Exception e){
         throw new RuntimeException("employee id not in database");
      }
   }

}
