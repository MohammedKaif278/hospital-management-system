package com.HospitalManagementSystem.dto;

import com.HospitalManagementSystem.Enum.ActiveStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {

	private Long patientId;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String phone;
    private String email;
    private boolean active;
}
