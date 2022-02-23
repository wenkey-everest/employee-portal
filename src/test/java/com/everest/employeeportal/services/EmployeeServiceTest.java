package com.everest.employeeportal.services;

import com.everest.employeeportal.exceptions.EmailIsRegisteredAlreadyException;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.models.Address;
import com.everest.employeeportal.models.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;


class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(null, "muni venkatesh", "Ganji", "wenkeygm@gmail.com", "123456", "muni.v.g@everest.engineering",
                LocalDate.of(1999, 8, 14), LocalDate.of(2021, 8, 3), "software craftperson",
                0, "Its better not be pro in somethings", new Address(null,"18-462, padma sali street",null,"venkatagiri", "andhra pradesh",524132, "India"),
                new Address(null,"18-462, padma sali street",null,"venkatagiri", "andhra pradesh",524132, "India"));
        employeeRepository= Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void EmployeeSavedSuccessfullyTest() {
        //Arrange
        given(employeeRepository.existsByEverestEmailId(employee.getEverestEmailId())).willReturn(false);
        given(employeeRepository.save(employee)).willAnswer(invocation -> invocation.getArgument(0));

        //Act
        Employee savedEmployee = employeeService.createEmployee(employee);

        //Assert
        assertThat(savedEmployee).isNotNull();

        then(employeeRepository).should().save(any(Employee.class));

    }

    @Test
    void ShouldThrowErrorWhenEverestEmailIdIsExists() {
        //arrange
        given(employeeRepository.existsByEverestEmailId(employee.getEverestEmailId())).willReturn(true);

        //assert
        assertThrows(EmailIsRegisteredAlreadyException.class, ()->{
           employeeService.createEmployee(employee);
        });

        then(employeeRepository).should(never()).save(any(Employee.class));
    }

    @Test
    void UpdateAndEmployeeExistByIdTest() {
        //arrange
        given(employeeRepository.existsById(employee.getEmpId())).willReturn(true);
        given(employeeRepository.findById(employee.getEmpId())).willReturn(Optional.of(employee));
        given(employeeRepository.save(employee)).willAnswer(invocation -> invocation.getArgument(0));

        //act
        Employee updateEmployee = employeeService.updateEmployee(employee, employee.getEmpId());
        Employee  employeeById = employeeService.getEmployeeById(employee.getEmpId()).get();

        //assert
        assertThat(updateEmployee).isNotNull();
        assertThat(employeeById).isNotNull();

        then(employeeRepository).should().save(any(Employee.class));
        then(employeeRepository).should().findById(employee.getEmpId());

    }
    @Test
    void ShouldThrowErrorWhenEmployeeIdExists() {
        //arrange
        given(employeeRepository.existsById(employee.getEmpId())).willReturn(false);
        given(employeeRepository.findById(employee.getEmpId())).willReturn(Optional.empty());
        //assert
        assertThrows(EmployeeNotFoundException.class, ()->{
            employeeService.updateEmployee(employee, employee.getEmpId());
        });
        assertThrows(EmployeeNotFoundException.class, ()->{
            employeeService.getEmployeeById(employee.getEmpId());
        });

        then(employeeRepository).should(never()).save(any(Employee.class));
    }

}