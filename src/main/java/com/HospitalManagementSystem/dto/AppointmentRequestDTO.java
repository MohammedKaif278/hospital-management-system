package com.HospitalManagementSystem.dto;

import java.time.LocalDateTime;

import com.HospitalManagementSystem.Enum.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {

	@JsonProperty("patient_id")
	private Long patientId;
	@JsonProperty("doctor_id")
	private Long doctorId;
	@JsonProperty("appointment_date")
	private LocalDateTime appointmentDate;
	
}
