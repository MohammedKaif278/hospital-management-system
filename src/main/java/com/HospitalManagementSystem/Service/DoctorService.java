package com.HospitalManagementSystem.Service;

import java.util.List;

import com.HospitalManagementSystem.dto.DoctorRequestDTO;
import com.HospitalManagementSystem.dto.DoctorResponseDTO;
import com.HospitalManagementSystem.entity.Doctor;

public interface DoctorService {

	DoctorResponseDTO  addDoctor(DoctorRequestDTO  doctorRequestDTO);
	
	DoctorResponseDTO  updateDoctor(Long doctorId,DoctorRequestDTO  doctorRequestDTO);
	
	DoctorResponseDTO  getActiveDoctorById(Long doctorId);
	
	List<DoctorResponseDTO> getAllActiveDoctors(); 
	
	List<DoctorResponseDTO > getAllDoctorsForAdmin();
	
	void deleteDoctor(Long doctorId);
	
	void activateDoctor(Long doctorId);
}
