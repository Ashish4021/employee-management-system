package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestDto {

    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain 10 digits")
    @Positive(message = "Enter positive number")
    private String phoneNumber;

    @Positive(message = "salary must be positive")
    private double salary;

    @NotBlank(message = "Domain is required")
    private String domain;
}
