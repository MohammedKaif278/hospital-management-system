package com.HospitalManagementSystem.mapper;

import com.HospitalManagementSystem.dto.AppointmentRequestDTO;
import com.HospitalManagementSystem.dto.AppointmentResponseDTO;
import com.HospitalManagementSystem.entity.Appointment;
import com.HospitalManagementSystem.entity.Doctor;
import com.HospitalManagementSystem.entity.Patient;

public class AppointmentMapper {

	public static Appointment toEntity(AppointmentRequestDTO dto, Patient patient, Doctor doctor) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointment;
    }

    public static AppointmentResponseDTO toDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStatus(appointment.getStatus());
        dto.setPatientId(appointment.getPatient().getPatientId());
        dto.setPatientName(appointment.getPatient().getName());
        dto.setPhone(appointment.getPatient().getPhone());
        dto.setDoctorId(appointment.getDoctor().getDoctorId());
        dto.setDoctorName(appointment.getDoctor().getName());
        dto.setSpeciality(appointment.getDoctor().getSpeciality());
        return dto;
    }
}
