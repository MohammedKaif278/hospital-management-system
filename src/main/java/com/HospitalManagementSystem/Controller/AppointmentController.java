package com.HospitalManagementSystem.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagementSystem.Enum.ResponseStatus;
import com.HospitalManagementSystem.Service.AppointmentService;
import com.HospitalManagementSystem.dto.AppointmentRequestDTO;
import com.HospitalManagementSystem.dto.AppointmentResponseDTO;
import com.HospitalManagementSystem.payload.APIResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hms/appointments")
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;

	// ================= ADD APPOINTMENT =================

	@PostMapping("/book")
	public ResponseEntity<APIResponse<AppointmentResponseDTO>> createAppointment(
			@RequestBody AppointmentRequestDTO dto) {
		AppointmentResponseDTO appointment = appointmentService.createAppointment(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.CREATED.value(), "Appointment Booked Successfully", appointment, LocalDateTime.now()));
	}

	// ================= UPDATE APPOINTMENT =================

	@PutMapping("/update/{appointmentId}")
	public ResponseEntity<APIResponse<AppointmentResponseDTO>> updateAppointment(@PathVariable Long appointmentId,
			@RequestBody AppointmentRequestDTO dto) {

		AppointmentResponseDTO updateAppointment = appointmentService.updateAppointment(appointmentId, dto);

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Appointment Update Successfully", updateAppointment, LocalDateTime.now()));

	}

	// =================  APPOINTMENT COMPLETED =================
	
	@PutMapping("/complete/{appointmentId}")
	public ResponseEntity<APIResponse<AppointmentResponseDTO>> completeAppointment(@PathVariable Long appointmentId) {

		AppointmentResponseDTO completed = appointmentService.completeAppointment(appointmentId);

		return ResponseEntity.ok(new APIResponse<>(ResponseStatus.SUCCESS.name(), HttpStatus.OK.value(),
				"Appointment completed successfully", completed, LocalDateTime.now()));
	}

	// ================= GET APPOINTMENT BY ID =================

	@GetMapping("/get/{appointmentId}")
	public ResponseEntity<APIResponse<AppointmentResponseDTO>> getAppointments(@PathVariable Long appointmentId) {
		AppointmentResponseDTO appointmentsByPatientId = appointmentService.getAppointmentsByPatientId(appointmentId);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new APIResponse<>(ResponseStatus.SUCCESS.name(), HttpStatus.OK.value(),
						"Appointment Fetch Successfully by appointmentId :- " + appointmentId, appointmentsByPatientId,
						LocalDateTime.now()));

	}

	// ================= GET APPOINTMENTS =================

	@GetMapping("/getall")
	public ResponseEntity<APIResponse<List<AppointmentResponseDTO>>> getAllAppointments() {
		List<AppointmentResponseDTO> allAppointments = appointmentService.getAllAppointments();

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "All Appointment Fetch Successfully ", allAppointments, LocalDateTime.now()));

	}

	// ================= DELETE APPOINTMENT BY ID =================

	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<APIResponse<AppointmentResponseDTO>> deleteAppointment(@PathVariable Long appointmentId) {
		AppointmentResponseDTO deletedAppointment = appointmentService.deleteAppointment(appointmentId);

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Appointment deleted successfully", deletedAppointment, LocalDateTime.now()));
	}

}
