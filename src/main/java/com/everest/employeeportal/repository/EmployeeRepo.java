package com.everest.employeeportal.repository;

import com.everest.employeeportal.config.DbConfig;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.resultSets.GetAllEmployees;
import com.everest.employeeportal.resultSets.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepo{
   private static final Map<Long, Employee>  employeeMap = new HashMap<>();

   private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EmployeeRepo(DbConfig dbConfig) {
        this.namedParameterJdbcTemplate = dbConfig.namedParameterJdbcTemplate();
    }

   public List<Employee> getAllEmployee(){
        return namedParameterJdbcTemplate.query("select * from employee_details join present_address on employee_details.empid = present_address.pre_id join permanent_address on present_address.pre_id = permanent_address.per_id", new GetAllEmployees());
   }

   public List<Employee> getEmployeeById(Long empID) {
        Map<String, Long> map = new HashMap<>();
                map.put("id", empID);
       return namedParameterJdbcTemplate.query("select * from employee_details join present_address on employee_details.empid = present_address.pre_id join permanent_address on present_address.pre_id = permanent_address.per_id where empid =" + map.get("id"), new GetAllEmployees());
   }

   public String createEmployee(Employee employee){
       List<Long> lastId = namedParameterJdbcTemplate.query("select max(empid) as last from employee_details", (rs, rowNum) -> rs.getLong("last"));
       employee.setEmpId(lastId.get(0)+1);
       employee.getPresentAddress().setAddId(lastId.get(0)+1);
       employee.getPermanentAddress().setAddId(lastId.get(0)+1);
       SqlParameterSource map = new ParameterMap().parameterSource(employee, employee.getEmpId());
       String details = "insert into employee_details values (:empId, :name,:everestEmailId, :password, :personalEmailId," +
               ":dateOfBirth,:dateOfJoin, :designation, :experience, :Bio, :presentAddressId, :permanentAddressId)";
       String presentAdd = "insert into present_address values (:presentAddressId, :presentAddressline1,:presentAddressline2, :presentAddressCity, :presentAddressState," +
               ":presentAddressZipCode,:presentAddressCountry)";
       String permanentAdd = "insert into permanent_address values (:permanentAddressId, :permanentAddressline1,:permanentAddressline2, :permanentAddressCity, :permanentAddressState," +
               ":permanentAddressZipCode,:permanentAddressCountry)";
        namedParameterJdbcTemplate.update(presentAdd, map);
        namedParameterJdbcTemplate.update(permanentAdd, map);
        namedParameterJdbcTemplate.update(details, map);
         return "Inserted successfully";
   }

   public String updateEmployee(Long empID, Employee employee) {
       List<Long> dbId = namedParameterJdbcTemplate.query("select empid from employee_details", (rs, rowNum) -> rs.getLong("empid"));
       if(dbId.contains(empID)){
           SqlParameterSource map = new ParameterMap().parameterSource(employee, empID);
       String details = "update employee_details set name=:name, everestemailid=:everestEmailId, password=:password, personalemailid=:personalEmailId," +
               "dateofbirth=:dateOfBirth,dateofjoin=:dateOfJoin, designation=:designation, experience=:experience, bio=:Bio, presentaddress=:presentAddressId, permanentaddress=:permanentAddressId where empid =:empId ";
       String presentAdd = "update present_address set pre_addressline1=:presentAddressline1, pre_addressline2=:presentAddressline2, pre_city=:presentAddressCity, pre_state=:presentAddressState," +
               "pre_zipcode=:presentAddressZipCode ,pre_country=:presentAddressCountry where pre_id =:empId ";
       String permanentAdd = "update permanent_address set per_addressline1=:permanentAddressline1,per_addressline2=:permanentAddressline2, per_city=:permanentAddressCity, per_state=:permanentAddressState," +
               "per_zipcode=:permanentAddressZipCode , per_country=:permanentAddressCountry where per_id =:empId ";
       namedParameterJdbcTemplate.update(presentAdd, map);
       namedParameterJdbcTemplate.update(permanentAdd, map);
       namedParameterJdbcTemplate.update(details, map);
       return "updated successfully";
   }
         throw new RuntimeException("employee id not in the database");
   }

  public String deleteEmployee(Long empId){
      try {
          SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                  .addValue("empId", empId);
          namedParameterJdbcTemplate.update("delete from employee_details where empid=:empId", sqlParameterSource);
         return "Deleted Successfully";
      }catch (Exception e){
         throw new RuntimeException("employee id not in database");
      }
   }

}
