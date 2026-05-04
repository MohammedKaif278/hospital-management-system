package com.HospitalManagementSystem.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagementSystem.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findByActiveTrue();
	
	Optional<Patient> findByPatientIdAndActiveTrue(Long patientId);
 }
