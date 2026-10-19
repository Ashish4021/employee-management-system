package com.example.demo.service;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.UpdateRequestDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeService.class);

    public void createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee emp = new Employee();
        emp.setFirstName(employeeRequestDto.getFirstName());
        emp.setLastName(employeeRequestDto.getLastName());
        emp.setEmail(employeeRequestDto.getEmail());
        emp.setPhoneNumber(employeeRequestDto.getPhoneNumber());
        emp.setDomain(employeeRequestDto.getDomain());
        emp.setSalary(employeeRequestDto.getSalary());
        emp.setJoiningDate(employeeRequestDto.getJoiningDate());
        Employee savedEmployee = employeeRepository.save(emp);
        log.info("Employee Created Successfully with ID: {}", savedEmployee.getId());
    }

    public List<EmployeeResponseDto> getAllEmployeeDetails(){
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeResponseDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getPhoneNumber(),
                        employee.getJoiningDate(),
                        employee.getSalary(),
                        employee.getDomain()
                ))
                .toList();
    }

    public Page<EmployeeResponseDto> getEmployeeWithPagination(Pageable pageable){
        return employeeRepository.findAll(pageable)
                .map(emp -> new EmployeeResponseDto(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail(),
                        emp.getPhoneNumber(),
                        emp.getJoiningDate(),
                        emp.getSalary(),
                        emp.getDomain()
                ));
    }


    public EmployeeResponseDto getEmployee(Long id){
        Employee response = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With this id : " + id));

        EmployeeResponseDto empDto = new EmployeeResponseDto(
                response.getId(),
                response.getFirstName(),
                response.getLastName(),
                response.getEmail(),
                response.getPhoneNumber(),
                response.getJoiningDate(),
                response.getSalary(),
                response.getDomain());
        return empDto;

    }

    public EmployeeResponseDto updateEmployee(Long id , UpdateRequestDto updateRequestDto){
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With this id : " + id));

        emp.setDomain(updateRequestDto.getDomain());
        emp.setSalary(updateRequestDto.getSalary());
        emp.setPhoneNumber(updateRequestDto.getPhoneNumber());

        Employee updatedResponse = employeeRepository.save(emp);

        EmployeeResponseDto responseDto = new EmployeeResponseDto(
                updatedResponse.getId(),
                updatedResponse.getFirstName(),
                updatedResponse.getLastName(),
                updatedResponse.getEmail(),
                updateRequestDto.getPhoneNumber(),
                updatedResponse.getJoiningDate(),
                updatedResponse.getSalary(),
                updatedResponse.getDomain()
        );
        return responseDto;
    }

    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with this id : " + id));
        employeeRepository.deleteById(id);
    }




}
