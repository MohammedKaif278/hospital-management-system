package com.HospitalManagementSystem.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.HospitalManagementSystem.dto.AppointmentRequestDTO;
import com.HospitalManagementSystem.dto.AppointmentResponseDTO;
import com.HospitalManagementSystem.entity.Appointment;

public interface AppointmentService {

//	Appointment bookAppointment(Long patientId,Long doctorId,LocalDateTime date);
//	
//	List<Appointment> getAppointmentsByPatientId(Long patientId);
//	
//	 Appointment cancelAppointment(Long appointmentId);

	AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO);

	AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO dto);

	AppointmentResponseDTO completeAppointment(Long appointmentId);
	
	AppointmentResponseDTO getAppointmentsByPatientId(Long appointmentId);

	List<AppointmentResponseDTO> getAllAppointments();

	AppointmentResponseDTO  deleteAppointment(Long appointmentId);
}
