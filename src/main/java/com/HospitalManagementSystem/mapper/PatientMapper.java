package com.HospitalManagementSystem.mapper;

import com.HospitalManagementSystem.dto.PatientRequestDTO;
import com.HospitalManagementSystem.dto.PatientResponseDTO;
import com.HospitalManagementSystem.entity.Patient;

public class PatientMapper {

	public static Patient toEntity(PatientRequestDTO dto)
	{
		Patient patient=new Patient();
		patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setAge(dto.getAge());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());
        patient.setEmail(dto.getEmail());
        patient.setActive(true);
        return patient;
	}
	
	
	public static PatientResponseDTO toDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getGender(),
                patient.getAge(),
                patient.getAddress(),
                patient.getPhone(),
                patient.getEmail(),
                patient.isActive()
        );
	}
}
