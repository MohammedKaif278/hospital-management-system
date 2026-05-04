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
import com.HospitalManagementSystem.Service.PatientService;
import com.HospitalManagementSystem.dto.PatientRequestDTO;
import com.HospitalManagementSystem.dto.PatientResponseDTO;
import com.HospitalManagementSystem.payload.APIResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hms/patients")
@RequiredArgsConstructor
public class PatientController {

	private final PatientService patientService;

	// ================= ADD PATIENT =================
	
	@PostMapping("/create")
	public ResponseEntity<APIResponse<PatientResponseDTO>> addPatient(
			@Valid @RequestBody PatientRequestDTO requestDTO) {
		PatientResponseDTO savedPatient = patientService.addPatient(requestDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.CREATED.value(), "Patient created successfully", savedPatient, LocalDateTime.now()));
	}

	// ================= UPDATE PATIENT =================
	@PutMapping("/update/{pateintid}")
	public ResponseEntity<APIResponse<PatientResponseDTO>> updatePatient(@PathVariable Long pateintId,
			@Valid @RequestBody PatientRequestDTO requestDTO) {

		PatientResponseDTO updatePatient = patientService.updatePatient(pateintId, requestDTO);

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Patient updated successfully", updatePatient, LocalDateTime.now()));
	}

	// ================= GET PATIENT BY ID =================
	@GetMapping("/get/{pateintId}")
	public ResponseEntity<APIResponse<PatientResponseDTO>> getActivePatientById(@PathVariable Long pateintId) {
		PatientResponseDTO patientById = patientService.getActivePatientById(pateintId);

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Patient fetched successfully", patientById, LocalDateTime.now()));
	}

	
	// ================= GET ALL PATIENTS =================
		@GetMapping("/getall")
		public ResponseEntity<APIResponse<List<PatientResponseDTO>>> getAllActivePatients() {
			List<PatientResponseDTO> allPatients = patientService.getAllActivePatients();
			return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
					HttpStatus.OK.value(), "All active patients fetched successfully ", allPatients, LocalDateTime.now()));
		}
	
	
	// ================= GET ALL PATIENTS ( ADMIN ) =================
	@GetMapping("/admin/getall")
	public ResponseEntity<APIResponse<List<PatientResponseDTO>>> getAllPatientsForAdmin() {
		List<PatientResponseDTO> allPatients = patientService.getAllPatientsForAdmin();
		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "All patients fetched successfully (Admin)", allPatients, LocalDateTime.now()));
	}

	// ================= DELETE PATIENT (SOFT DELETE) =================
	@DeleteMapping("/delete/{pateintId}")
	public ResponseEntity<APIResponse<Void>> deletePatient(@PathVariable Long pateintId) {
		patientService.deletePatient(pateintId);
		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Patient deleted successfully", null, LocalDateTime.now()));

	}

	// ================= Activate Patient =================
	@PutMapping("/activate/{pateintId}")
	public ResponseEntity<APIResponse<String>> activatePatient(@PathVariable Long pateintId) {
		patientService.activatePatient(pateintId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new APIResponse<>(ResponseStatus.SUCCESS.name(), HttpStatus.OK.value(),
						"Patient activated successfully", "Patient ID " + pateintId + " is now ACTIVE",
						LocalDateTime.now()));

	}

}
