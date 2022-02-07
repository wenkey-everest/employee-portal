package com.everest.employeeportal.resultSets;

import com.everest.employeeportal.models.Address;
import com.everest.employeeportal.models.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllEmployees implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(rs.getLong("empid"),rs.getString("name"), rs.getString("everestemailid")
        , rs.getString("password"), rs.getString("personalemailid"), rs.getDate("dateofbirth").toLocalDate(), rs.getDate("dateofjoin").toLocalDate(),
                rs.getString("designation"), rs.getInt("experience"), rs.getString("bio"),
                new Address(rs.getLong("pre_id"), rs.getString("pre_addressline1"), rs.getString("pre_addressline2"), rs.getString("pre_city"), rs.getString("pre_state"),
                rs.getInt("pre_zipcode"), rs.getString("pre_country")),
                new Address(rs.getLong("per_id"), rs.getString("per_addressline1"), rs.getString("per_addressline2"), rs.getString("per_city"), rs.getString("per_state"),
                rs.getInt("per_zipcode"), rs.getString("per_country")));
    }
}
