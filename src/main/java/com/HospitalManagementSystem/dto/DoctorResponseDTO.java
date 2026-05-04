package com.HospitalManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {

	private Long doctorId;
    private String name;
    private String speciality;
    private Boolean available;
    private String phone;
    private String email;
    private Boolean active;
}
