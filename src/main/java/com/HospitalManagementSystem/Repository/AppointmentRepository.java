package com.HospitalManagementSystem.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagementSystem.entity.Appointment;
import com.HospitalManagementSystem.entity.Doctor;
import com.HospitalManagementSystem.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatient_PatientId(Long patientId);

	boolean existsByDoctor_DoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
	
	 List<Appointment> findByDoctor(Doctor doctor);
	    List<Appointment> findByPatient(Patient patient);
}
