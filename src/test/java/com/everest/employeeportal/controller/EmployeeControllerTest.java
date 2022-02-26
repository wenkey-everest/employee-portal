package com.everest.employeeportal.controller;

import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.exceptions.RequiredRequestParamException;
import com.everest.employeeportal.models.Address;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @Autowired
 private ObjectMapper objectMapper;

 @MockBean
 private EmployeeService employeeService;


 private  Employee employee;

 private List<Employee> employeeList;


 @BeforeEach
 void setUp() {

  employeeList = new ArrayList<>();
  employee = new Employee(1L, "muni venkatesh", "Ganji", "muni.v.g@everest.engineering", "12345678", "wenkeygm@gmail.com",
          LocalDate.of(1999, 8, 14), LocalDate.of(2021, 8, 3), "software craftperson",
          0, "Its better not be pro in somethings", new Address(null,"18-462, padma sali street",null,"venkatagiri", "andhra pradesh",524132, "India"),
          new Address(null,"18-462, padma sali street",null,"venkatagiri", "andhra pradesh",524132, "India"));
  employeeList.add(employee);

 }
 @Test
 void shouldFetchAllEmployees() throws Exception {

  Page<Employee> employeePage = new PageImpl<>(employeeList);
  given(employeeService.getAllEmployees(any(Pageable.class))).willReturn(employeePage);

  mockMvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content(objectMapper.writeValueAsString(employeePage.getPageable())))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.pageSize", is(employeePage.getSize())));
 }

 @Test
 void ShouldFetchEmployeeById() throws Exception {

  given(employeeService.getEmployeeById(employee.getEmpId())).willReturn(Optional.of(employee));

  mockMvc.perform(get("/api/employees/{id}", employee.getEmpId()))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.everestEmailId", is(employee.getEverestEmailId())))
          .andExpect(jsonPath("$.password", is(employee.getPassword())))
          .andExpect(jsonPath("$.firstName", is(employee.getFirstName())));
 }

 @Test
 void ShouldReturnNotFoundOnWhenEmployeeIsAbsent() throws Exception {
  given(employeeService.getEmployeeById(employee.getEmpId())).willThrow(new EmployeeNotFoundException(employee.getEmpId()));

  this.mockMvc.perform(get("/api/employees/{id}", employee.getEmpId())).andDo(print())
          .andExpect(status().isNotFound())
          .andExpect(jsonPath("$.message",is("Employee not found with Id "+employee.getEmpId())));
 }

 @Test
 void ShouldCreateNewEmployee() throws Exception{

  given(employeeService.createEmployee(any(Employee.class)))
          .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

  mockMvc.perform(post("/api/employees")
          .content(objectMapper.writeValueAsString(employee)).contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.everestEmailId", is(employee.getEverestEmailId())))
          .andExpect(jsonPath("$.password", is(employee.getPassword())))
          .andExpect(jsonPath("$.firstName", is(employee.getFirstName())));

 }

 @Test
 void ShouldUpdateEmployee() throws Exception{

  given(employeeService.updateEmployee(any(Employee.class),eq(employee.getEmpId())))
          .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

  mockMvc.perform(put("/api/employees/{id}", employee.getEmpId())
          .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(employee)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.everestEmailId", is(employee.getEverestEmailId())))
          .andExpect(jsonPath("$.password", is(employee.getPassword())))
          .andExpect(jsonPath("$.firstName", is(employee.getFirstName())));
 }

 @Test
 void shouldReturnBadRequestWhenEmployeeNotFoundOnUpdate() throws Exception {

  given(employeeService.getEmployeeById(employee.getEmpId())).willThrow(EmployeeNotFoundException.class);

  this.mockMvc.perform(put("/api/users/{id}", employee.getEmpId())
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content(objectMapper.writeValueAsString(employee))).andDo(print())
          .andExpect(status().isNotFound());

 }


 @Test
 void ShouldDeleteEmployeeById() throws Exception {

  doNothing().when(employeeService).deleteEmployee(employee.getEmpId());

  mockMvc.perform(delete("/api/employees/{id}", employee.getEmpId())
          .content(objectMapper.writeValueAsString(employee.getEmpId())))
          .andExpect(status().isOk());

 }
 @Test
 void shouldReturn404WhenEmployeeIsNotFoundOnDelete() throws Exception {
  given(employeeService.getEmployeeById(employee.getEmpId())).willThrow(EmployeeNotFoundException.class);

  this.mockMvc.perform(delete("/api/users/{id}", employee.getEmpId()))
          .andExpect(status().isNotFound());

 }

 @Test
 void ShouldDeleteAllEmployees() throws Exception {
  doNothing().when(employeeService).truncateEmployeeDetails();

  mockMvc.perform(delete("/api/employees"))
          .andExpect(status().isOk());

 }

 @Test
 void ShouldGetEmployeeOnSearchByLastName() throws Exception{
  Page<Employee> employeePage = new PageImpl<>(employeeList);
  given(employeeService.searchEmployeeByName(eq("Ganji"),any(Pageable.class))).willReturn(employeePage);

  mockMvc.perform(get("/api/employees/search")
          .content(objectMapper.writeValueAsString(employeePage.getPageable())).param("name", "Ganji"))
          .andExpect(status().isFound())
          .andExpect(jsonPath("$.data[0].firstName", is(employee.getFirstName())))
          .andExpect(jsonPath("$.data[0].everestEmailId", is(employee.getEverestEmailId())))
          .andExpect(jsonPath("$.data[0].password", is(employee.getPassword())));

 }
 @Test
 void ShouldGetEmployeeOnSearchByFirstName() throws Exception{
  Page<Employee> employeePage = new PageImpl<>(employeeList);
  given(employeeService.searchEmployeeByName(eq("muni venkatesh"),any(Pageable.class))).willReturn(employeePage);

  mockMvc.perform(get("/api/employees/search")
                  .content(objectMapper.writeValueAsString(employeePage.getPageable())).param("name", "muni venkatesh"))
          .andExpect(status().isFound())
          .andExpect(jsonPath("$.data[0].firstName", is(employee.getFirstName())))
          .andExpect(jsonPath("$.data[0].everestEmailId", is(employee.getEverestEmailId())))
          .andExpect(jsonPath("$.data[0].password", is(employee.getPassword())));

 }

 @Test
 void ShouldGet404WhenNameParamIsAbsent() throws Exception{
  Page<Employee> employeePage = new PageImpl<>(employeeList);
  given(employeeService.searchEmployeeByName(eq("muni venkatesh"),any(Pageable.class))).willThrow(RequiredRequestParamException.class);

  mockMvc.perform(get("/api/employees/search")
                  .content(objectMapper.writeValueAsString(employeePage.getPageable())))
          .andExpect(status().isBadRequest());

 }
}