package com.HospitalManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {

	@NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Speciality is required")
    private String speciality;

    private Boolean available;

    @Size(min = 10, max = 15, message = "Invalid phone number")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    
}
