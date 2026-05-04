package com.HospitalManagementSystem.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.Enum.ActiveStatus;
import com.HospitalManagementSystem.Repository.PatientRepository;
import com.HospitalManagementSystem.Service.PatientService;
import com.HospitalManagementSystem.dto.PatientRequestDTO;
import com.HospitalManagementSystem.dto.PatientResponseDTO;
import com.HospitalManagementSystem.entity.Patient;
import com.HospitalManagementSystem.exception.ResourceNotFoundException;
import com.HospitalManagementSystem.mapper.PatientMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	@Override
	public PatientResponseDTO addPatient(PatientRequestDTO requestDTO) {

		Patient patient = PatientMapper.toEntity(requestDTO);
		Patient saved = patientRepository.save(patient);
		return PatientMapper.toDTO(saved);
	}

	@Override
	public PatientResponseDTO updatePatient(Long patientId, PatientRequestDTO requestDTO) {
		
		
		
		Patient existing = patientRepository.findById(patientId).orElseThrow(
				() -> new ResourceNotFoundException("Patient not foundd with this Patient ID :- " + patientId));
		

		existing.setName(requestDTO.getName());
		existing.setAge(requestDTO.getAge());
		existing.setGender(requestDTO.getGender());
		existing.setPhone(requestDTO.getPhone());
		existing.setEmail(requestDTO.getEmail());
		existing.setAddress(requestDTO.getAddress());
		Patient updated = patientRepository.save(existing);
		return PatientMapper.toDTO(updated);
	}

	@Override
	public PatientResponseDTO getActivePatientById(Long patientId) {

		Patient patient = patientRepository.findByPatientIdAndActiveTrue(patientId).orElseThrow(
				() -> new ResourceNotFoundException("Active Patient not foundd with this Patient ID :- " + patientId));
		return PatientMapper.toDTO(patient);
	}

	@Override
	public List<PatientResponseDTO> getAllActivePatients() {
		return patientRepository.findByActiveTrue()
				.stream()
				.map(PatientMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PatientResponseDTO> getAllPatientsForAdmin() {
		return patientRepository.findAll()
				.stream()
				.map(PatientMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void deletePatient(Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(
				() -> new ResourceNotFoundException("Patient not foundd with this Patient ID :- " + patientId));
		
		patient.setActive(false);
		patientRepository.save(patient);

	}

	@Override
	public void activatePatient(Long patientId) {
		 Patient patient = patientRepository.findById(patientId)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Patient not found with ID: " + patientId));
		 patient.setActive(true);
	        patientRepository.save(patient);
	}

}
