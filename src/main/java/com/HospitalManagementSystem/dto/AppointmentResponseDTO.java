package com.HospitalManagementSystem.dto;

import java.time.LocalDateTime;

import com.HospitalManagementSystem.Enum.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {

	private Long appointmentId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private Long patientId;
    private String patientName;
    private String phone;
    private Long doctorId;
    private String doctorName;
    private String speciality;
}
