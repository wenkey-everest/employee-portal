package com.everest.employeeportal.resultSets;

import com.everest.employeeportal.models.Employee;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class ParameterMap {

    public SqlParameterSource parameterSource(Employee employee, Long empID){
        return new MapSqlParameterSource()
                .addValue("empId", empID)
                .addValue("name", employee.getName())
                .addValue("everestEmailId", employee.getEverestEmailId())
                .addValue("password", employee.getPassword())
                .addValue("personalEmailId", employee.getPersonalEmailId())
                .addValue("dateOfBirth", employee.getDateOfBirth())
                .addValue("dateOfJoin", employee.getDateOfJoin())
                .addValue("designation", employee.getDesignation())
                .addValue("experience", employee.getExperience())
                .addValue("Bio", employee.getBio())
                .addValue("presentAddressId", employee.getPresentAddress().getAddId())
                .addValue("presentAddressline1", employee.getPresentAddress().getAddressLine1())
                .addValue("presentAddressline2", employee.getPresentAddress().getAddressLine2())
                .addValue("presentAddressCity", employee.getPresentAddress().getCity())
                .addValue("presentAddressState", employee.getPresentAddress().getState())
                .addValue("presentAddressZipCode", employee.getPresentAddress().getZipCode())
                .addValue("presentAddressCountry", employee.getPresentAddress().getCountry())
                .addValue("permanentAddressId", employee.getPermanentAddress().getAddId())
                .addValue("permanentAddressline1", employee.getPermanentAddress().getAddressLine1())
                .addValue("permanentAddressline2", employee.getPresentAddress().getAddressLine2())
                .addValue("permanentAddressCity", employee.getPermanentAddress().getCity())
                .addValue("permanentAddressState", employee.getPermanentAddress().getState())
                .addValue("permanentAddressZipCode", employee.getPermanentAddress().getZipCode())
                .addValue("permanentAddressCountry", employee.getPermanentAddress().getCountry());

    }
}
