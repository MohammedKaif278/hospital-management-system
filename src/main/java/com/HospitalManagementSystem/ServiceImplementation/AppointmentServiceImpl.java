package com.HospitalManagementSystem.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.Enum.AppointmentStatus;
import com.HospitalManagementSystem.Repository.AppointmentRepository;
import com.HospitalManagementSystem.Repository.DoctorRepository;
import com.HospitalManagementSystem.Repository.PatientRepository;
import com.HospitalManagementSystem.Service.AppointmentService;
import com.HospitalManagementSystem.dto.AppointmentRequestDTO;
import com.HospitalManagementSystem.dto.AppointmentResponseDTO;
import com.HospitalManagementSystem.entity.Appointment;
import com.HospitalManagementSystem.entity.Doctor;
import com.HospitalManagementSystem.entity.Patient;
import com.HospitalManagementSystem.exception.ResourceNotFoundException;
import com.HospitalManagementSystem.mapper.AppointmentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	private final AppointmentRepository appointmentRepository;

	@Override
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		Patient patient = patientRepository.findByPatientIdAndActiveTrue(appointmentRequestDTO.getPatientId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Patient is not found with this Patient ID :- " + appointmentRequestDTO.getPatientId()));

		Doctor doctor = doctorRepository.findById(appointmentRequestDTO.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor not foundd"));

		// Check whether doctor is available or not
		if (!doctor.getAvailable()) {
			throw new RuntimeException("Doctor is not available");
		}

		// Check whether appointment is already booked or not
		boolean alreadyBooked = appointmentRepository.existsByDoctor_DoctorIdAndAppointmentDate(
				appointmentRequestDTO.getDoctorId(), appointmentRequestDTO.getAppointmentDate());

		// If already booked then throw exception
		if (alreadyBooked) {
			throw new RuntimeException("Doctor already has an appointment at this time");
		}

		Appointment appointment = Appointment.builder().appointmentDate(appointmentRequestDTO.getAppointmentDate())
				.status(AppointmentStatus.BOOKED) // ✅ DEFAULT STATUS
				.patient(patient).doctor(doctor).build();

		Appointment saved = appointmentRepository.save(appointment);
		return AppointmentMapper.toDTO(saved);

	}

	@Override
	public AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO dto) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Appointment not found with this Appoinment ID :- " + appointmentId));

		if (dto.getAppointmentDate() != null)
			appointment.setAppointmentDate(dto.getAppointmentDate());
//        if (dto.getStatus() != null) appointment.setStatus(dto.getStatus());
		if (dto.getPatientId() != null) {
			Patient patient = patientRepository.findById(dto.getPatientId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Patient not found for this Patient ID :- " + dto.getPatientId()));
			appointment.setPatient(patient);
		}
		if (dto.getDoctorId() != null) {
			Doctor doctor = doctorRepository.findById(dto.getDoctorId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Doctor not foundfor this Patient ID :- " + dto.getPatientId()));
			appointment.setDoctor(doctor);
		}

		return AppointmentMapper.toDTO(appointmentRepository.save(appointment));

	}

	@Override
	public AppointmentResponseDTO getAppointmentsByPatientId(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Appointment not found with this Appoinment ID :- " + appointmentId));

		return AppointmentMapper.toDTO(appointment);
	}

	@Override
	public List<AppointmentResponseDTO> getAllAppointments() {
		return appointmentRepository.findAll().stream().map(AppointmentMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public AppointmentResponseDTO deleteAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID " + appointmentId));

		appointmentRepository.delete(appointment);

		return AppointmentMapper.toDTO(appointment);
	}

	@Override
	public AppointmentResponseDTO completeAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID :- " + appointmentId));

		if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
			throw new RuntimeException("Appointment is already completed");
		}

		
		  appointment.setStatus(AppointmentStatus.COMPLETED);
		  
		  appointmentRepository.save(appointment);
		
		return AppointmentMapper.toDTO(appointment);
	}

}
