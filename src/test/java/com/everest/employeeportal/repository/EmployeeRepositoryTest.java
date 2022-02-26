package com.everest.employeeportal.repository;

import com.everest.employeeportal.models.Address;
import com.everest.employeeportal.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        entityManager.clear();
     employee  = new Employee(null, "muni venkatesh", "Ganji", "muni.v.g@everest.engineering", "12345678", "wenkeygm@gmail.com",
                LocalDate.of(1999, 8, 14), LocalDate.of(2021, 8, 3), "software craftperson",
                0, "Its better not be pro in somethings", new Address(null,"18-462, padma sali street",null,"venkatagiri", "andhra pradesh",52413, "India"),
                new Address(null,"18-462, padma sali street",null,"venkatagiri", "andhra pradesh",52413, "India"));
      entityManager.persist(employee);
    }

    @Test
    void shouldSearchEmployeeByName() {
        Page<Employee> employeePage = employeeRepository.findByName("muni venkatesh", any(Pageable.class));
        assertThat(employeePage).contains(employee);
    }

    @Test
    void ShouldExistByEverestEmailId() {
        boolean isEmployeePresentByEverestEmailId = employeeRepository.existsByEverestEmailId(employee.getEverestEmailId());
        assertThat(isEmployeePresentByEverestEmailId).isTrue();
    }
}