package com.example.demo.service;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.UpdateRequestDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    //Happy Case
    @Test
    void getEmployee_whenEmployeeExists_ReturnsEmployeeDto(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Rahul");
        employee.setLastName("Kumar");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponseDto result = employeeService.getEmployee(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Rahul", result.getFirstName());


        verify(employeeRepository).findById(1L);

    }

    @Test
    void getEmployee_whenEmployeeDoesNotExist_ThrowException(){
       when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

       assertThrows(ResourceNotFoundException.class,
               () -> employeeService.getEmployee(99l));

       verify(employeeRepository).findById(99L);
    }

    @Test
    void createEmployee_ShouldSaveEmployee(){
        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setFirstName("Rahul");
        request.setLastName("Kumar");

        employeeService.createEmployee(request);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void createEmployee_ShouldSaveCorrectDetails(){
        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setFirstName("Rahul");
        request.setPhoneNumber("8920504750");

        employeeService.createEmployee(request);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(captor.capture());
        Employee savedEmployee = captor.getValue();

        assertEquals("Rahul", savedEmployee.getFirstName());
        assertEquals("8920504750", savedEmployee.getPhoneNumber());
    }

    @Test
    void updateEmployee_WhenEmployeeExists_ReturnsUpdatedDto(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setDomain("Tech");
        employee.setSalary(30000);

        UpdateRequestDto request = new UpdateRequestDto();
        request.setDomain("SpringBoot");
        request.setSalary(40000);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeResponseDto result = employeeService.updateEmployee(1L, request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("SpringBoot", result.getDomain());


        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void updateEmployee_WhenEmployeeDoesNotExist_ThrowsException(){
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                employeeService.updateEmployee(99L, new UpdateRequestDto()));

        verify(employeeRepository).findById(99L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

//    @Test
//    void deleteEmployee_ShouldCallRepositoryDeleteById(){
//        Long employeeId = 1L;
//
//        employeeService.deleteEmployee(employeeId);
//        verify(employeeRepository, times(1)).deleteById(employeeId);
//    }

    @Test
    void deleteEmployee_WhenEmployeeDoesNotExist_ThrowException(){
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                employeeService.deleteEmployee(99L));

        verify(employeeRepository).findById(99L);
    }

    @Test
    void getAllEmployeeDetails_ShouldReturnEmployeeDtoList(){
        Employee emp1 = new Employee();
        emp1.setFirstName("Rahul");
        emp1.setLastName("Kumar");

        Employee emp2 = new Employee();
        emp2.setFirstName("Priya");
        emp2.setLastName("salojni");

        when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2));

        List<EmployeeResponseDto> result = employeeService.getAllEmployeeDetails();

        assertEquals(2, result.size());
        assertEquals("Rahul", result.get(0).getFirstName());
        assertEquals("Priya", result.get(1).getFirstName());

        verify(employeeRepository).findAll();

    }

}
