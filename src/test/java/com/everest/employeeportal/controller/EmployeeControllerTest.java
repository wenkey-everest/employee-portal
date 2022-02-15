package com.everest.employeeportal.controller;

import com.everest.employeeportal.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@WebMvcTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

   @MockBean
   EmployeeRepository employeeRepository;

    @Autowired
    EmployeeController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String user = "{\"firstName\": \"bob\", \"PersonalEmail\" : \"bob@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

    @Test
    void should_no_employees_if_repository_is_empty() {

    }

    @Test
    void sortAllEmployees() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void createEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void testDeleteEmployee() {
    }

    @Test
    void searchByName() {
    }
}