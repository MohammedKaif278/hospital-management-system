package com.HospitalManagementSystem.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.Repository.DoctorRepository;
import com.HospitalManagementSystem.Service.DoctorService;
import com.HospitalManagementSystem.dto.DoctorRequestDTO;
import com.HospitalManagementSystem.dto.DoctorResponseDTO;
import com.HospitalManagementSystem.entity.Doctor;
import com.HospitalManagementSystem.exception.ResourceNotFoundException;
import com.HospitalManagementSystem.mapper.DoctorMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;

	@Override
	public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO) {

		Doctor doctor = DoctorMapper.toEntity(doctorRequestDTO);
		Doctor saved = doctorRepository.save(doctor);
		return DoctorMapper.toDTO(saved);
	}

	@Override
	public DoctorResponseDTO updateDoctor(Long doctorId, DoctorRequestDTO doctorRequestDTO) {
		Doctor existing = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));

		existing.setName(doctorRequestDTO.getName());
		existing.setSpeciality(doctorRequestDTO.getSpeciality());
		existing.setAvailable(doctorRequestDTO.getAvailable());
		existing.setPhone(doctorRequestDTO.getPhone());
		existing.setEmail(doctorRequestDTO.getEmail());
		

		Doctor updated = doctorRepository.save(existing);

		return DoctorMapper.toDTO(updated);
	}

	@Override
	public DoctorResponseDTO getActiveDoctorById(Long doctorId) {

		Doctor doctor = doctorRepository.findByDoctorIdAndActiveTrue(doctorId).orElseThrow(
				() -> new ResourceNotFoundException("Doctor not found with this Doctor ID :- " + doctorId));

		return DoctorMapper.toDTO(doctor);

	}

	@Override
	public List<DoctorResponseDTO> getAllActiveDoctors() {

		return doctorRepository.findByActiveTrue()
				.stream()
				.map(DoctorMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<DoctorResponseDTO> getAllDoctorsForAdmin() {
		return doctorRepository.findAll()
				.stream()
				.map(DoctorMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteDoctor(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
		doctor.setActive(false);

		doctorRepository.save(doctor);

	}

	@Override
	public void activateDoctor(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
		doctor.setActive(true);
		doctorRepository.save(doctor);
	}

}
