package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.UpdateRequestDto;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
@Tag(
        name = "Employee Management",
        description = "APIs for managing employees"
)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        employeeService.createEmployee(employeeRequestDto);
        return ResponseEntity.ok("Employee Saved SuccessFully!");
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployee(){
        List<EmployeeResponseDto> response = employeeService.getAllEmployeeDetails();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Page")
    public Page<EmployeeResponseDto> getEmployeewithpage(@PageableDefault(size=5)
                                                             Pageable pageable){
        return employeeService.getEmployeeWithPagination(pageable);
    }

    @Operation(
            summary = "Get Employee by ID",
            description = "Fetches an employee using their unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee Found Successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable Long id){
        EmployeeResponseDto responseDto = employeeService.getEmployee(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id ,
                                                 @Valid @RequestBody UpdateRequestDto updateRequestDto){
        EmployeeResponseDto responseDto = employeeService.updateEmployee(id, updateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "Employee Record Deleated SuccessFully!";
    }


}
