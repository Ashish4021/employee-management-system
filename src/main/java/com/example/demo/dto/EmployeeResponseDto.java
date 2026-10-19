package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {

    @Schema(description = "Unique employee ID", example = "101")
    private Long id;

    @Schema(description = "Employee's first name", example = "Rahul")
    private String firstName;

    @Schema(description = "Employee's last name", example = "kumar")
    private String lastName;

    @Schema(description = "Employee's email", example = "rahul@gmail.com")
    private String email;

    @Schema(description = "Employee's phoneNumber", example = "9876543210")
    private String phoneNumber;

    @Schema(description = "Employee's joining date", example = "2026-01-15")
    private LocalDate joiningDate;

    @Schema(description = "Employee's Salary", example = "50000")
    private double salary;

    @Schema(description = "Employee's domain", example = "Java Development")
    private String domain;
}
