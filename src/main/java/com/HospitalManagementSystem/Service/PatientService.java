package com.HospitalManagementSystem.Service;

import java.util.List;

import com.HospitalManagementSystem.dto.PatientRequestDTO;
import com.HospitalManagementSystem.dto.PatientResponseDTO;

public interface PatientService {

	PatientResponseDTO addPatient(PatientRequestDTO  requestDTO);
	
	PatientResponseDTO updatePatient(Long patientId, PatientRequestDTO  requestDTO);
	
	PatientResponseDTO getActivePatientById(Long patientId);
	
	List<PatientResponseDTO> getAllActivePatients();
	
	List<PatientResponseDTO> getAllPatientsForAdmin();
	
	void deletePatient(Long patientId);
	
	void activatePatient(Long patientId);
}
