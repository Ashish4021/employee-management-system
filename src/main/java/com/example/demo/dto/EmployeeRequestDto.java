package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeRequestDto {
    @Schema(description = "Employee's first name",
            example = "Rahul")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(description = "Employee's last name",
            example = "kumar")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(description = "Employee's Email Should be here",
            example = "Rahul@gmail.com")
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;


    @Pattern(regexp = "^[0-9]{10}$",
    message = "Phone number must contain 10 digits")
    @Positive(message = "Enter positive number")
    private String phoneNumber;

    @Schema(description = "Employee's joining date",
            example = "2026-01-15")
    @NotNull
    @PastOrPresent(message = "Date should be past or present")
    private LocalDate joiningDate;

    @Schema(description = "Employee's salary",
            example = "50000")
    @Positive(message = "salary must be positive")
    private double salary;

    @Schema(description = "Employee's work domain",
            example = "Java Development")
    @NotBlank(message = "Domain is required")
    private String domain;
}
