package com.HospitalManagementSystem.mapper;

import com.HospitalManagementSystem.dto.DoctorRequestDTO;
import com.HospitalManagementSystem.dto.DoctorResponseDTO;
import com.HospitalManagementSystem.entity.Doctor;

public class DoctorMapper {
	public static Doctor toEntity(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpeciality(dto.getSpeciality());
        doctor.setPhone(dto.getPhone());
        doctor.setEmail(dto.getEmail());
        doctor.setAvailable(dto.getAvailable() != null ? dto.getAvailable() : true);
        doctor.setActive(true);
        return doctor;
    }

    public static DoctorResponseDTO toDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getDoctorId(),
                doctor.getName(),
                doctor.getSpeciality(),
                doctor.getAvailable(),
                doctor.getPhone(),
                doctor.getEmail(),
                doctor.getActive()
        );
    }
}






