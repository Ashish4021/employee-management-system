package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.UpdateRequestDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void getEmployee_ShouldReturnEmployee_WhenIdExists() throws Exception{
        EmployeeResponseDto employee = new EmployeeResponseDto(
                1L,
                "Rahul",
                "kumar",
                "rahul@gmail.com",
                "8920504750",
                LocalDate.now(),
                9000,
                "Tech"
        );
        when(employeeService.getEmployee(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employee/ 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Rahul"));
    }

    @Test
    void getEmployee_WhenEmployeeDoesNotExist_ReturnNotFound() throws Exception{
        when(employeeService.getEmployee(99L)).thenThrow(new ResourceNotFoundException(
                "Employee Not Found with this id : 99"));
        mockMvc.perform(get("/api/employee/ 99  "))
                .andExpect(status().isNotFound());
    }

    @Test
    void createEmployee_ShouldReturnSuccessMessage() throws Exception{
        String requestBody = """
                {
                     "firstName" : "Nishant",
                     "lastName" : "pal",
                     "email" : "nishant7628@gmail.com",
                     "phoneNumber" : "5752672371",
                     "domain" : "It",
                     "salary" : 100000,
                     "joiningDate" : "2026-10-09"
                 }
                """;

        mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee Saved SuccessFully!"));
        verify(employeeService).createEmployee(any(EmployeeRequestDto.class));

    }

    @Test
    void createEmployee_WhenRequestIsInvalid_ReturnsBadRequest() throws Exception{
        String requestBody = """
                 {
                     "firstName" : "",
                     "lastName" : "pal",
                     "email" : "nishant7628@gmail.com",
                     "phoneNumber" : "52371",
                     "domain" : "It",
                     "salary" : 100000,
                     "joiningDate" : "2026-10-09"
                 }
                """;
        mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());

        verify(employeeService, never())
                .createEmployee(any(EmployeeRequestDto.class));
    }

    @Test
    void updateEmployee_ShouldReturnUpdateEmployee() throws Exception{
        String requestBody = """
                {
                     "domain" : "Spring Boot",
                     "salary" : 60000,
                     "phoneNumber" : "9876543210"
                 }
                """;
        EmployeeResponseDto updatedEmployee = new EmployeeResponseDto(
                1L,
                "Rahul",
                "kumar",
                "rahul@gmail.com",
                "9876543210",
                LocalDate.now(),
                60000,
                "Spring Boot"
        );

        when(employeeService.updateEmployee(eq(1L),any(UpdateRequestDto.class)))
                .thenReturn(updatedEmployee);
        mockMvc.perform(put("/api/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.domain").value("Spring Boot"))
                .andExpect(jsonPath("$.phoneNumber").value("9876543210"));

        verify(employeeService).updateEmployee(eq(1L),any(UpdateRequestDto.class));
    }

    @Test
    void updateEmployee_WhenRequestIsInvalid_ReturnsBadRequest() throws Exception{
        String requestBody = """
                {
                     "domain" : "Spring Boot",
                     "salary" : 60000,
                     "phoneNumber" : "123"
                 }
                """;
        mockMvc.perform(put("/api/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());

        verify(employeeService, never()).updateEmployee(eq(1L),any(UpdateRequestDto.class));

    }

    @Test
    void deleteEmployee_ShouldReturnSuccessMessage() throws Exception{
        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee Record Deleated SuccessFully!"));

        verify(employeeService).deleteEmployee(1L);
    }




}
