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
import com.HospitalManagementSystem.Service.DoctorService;
import com.HospitalManagementSystem.dto.DoctorRequestDTO;
import com.HospitalManagementSystem.dto.DoctorResponseDTO;
import com.HospitalManagementSystem.payload.APIResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/hms/doctors")
@RequiredArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;
	
	// ================= ADD DOCTOR =================
	
	@PostMapping("/create")
	public ResponseEntity<APIResponse<DoctorResponseDTO>> addDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {		
		DoctorResponseDTO savedDoctor = doctorService.addDoctor(doctorRequestDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.CREATED.value(), "Doctor created successfully", savedDoctor, LocalDateTime.now()));
	}
	
	
	// ================= UPDATE DOCTOR =================
	
	@PutMapping("/update/{doctorId}")
	public ResponseEntity<APIResponse<DoctorResponseDTO>> updateDoctor(@PathVariable Long doctorId,@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {

		DoctorResponseDTO updateDoctor = doctorService.updateDoctor(doctorId, doctorRequestDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(), HttpStatus.OK.value(), "Doctor updated successfully", updateDoctor, LocalDateTime.now()));
	}
	
	
	// ================= GET DOCTOR BY ID =================
	@GetMapping("/get/{doctorId}")
	public ResponseEntity<APIResponse<DoctorResponseDTO>> getActivePatientById(@PathVariable Long doctorId) {
		DoctorResponseDTO doctorById = doctorService.getActiveDoctorById(doctorId);

		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Doctor fetched successfully", doctorById, LocalDateTime.now()));
	}

	
	// ================= GET ALL DOCTOR =================
		@GetMapping("/getall")
		public ResponseEntity<APIResponse<List<DoctorResponseDTO>>> getAllActivePatients() {
			List<DoctorResponseDTO> allDoctors = doctorService.getAllActiveDoctors();
			return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
					HttpStatus.OK.value(), "All active doctors fetched successfully ", allDoctors, LocalDateTime.now()));
		}
	
	
	// ================= GET ALL DOCTOR ( ADMIN ) =================
	@GetMapping("/admin/getall")
	public ResponseEntity<APIResponse<List<DoctorResponseDTO>>> getAllPatientsForAdmin() {
		List<DoctorResponseDTO> allDoctors = doctorService.getAllDoctorsForAdmin();
		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "All doctors fetched successfully (Admin)", allDoctors, LocalDateTime.now()));
	}

	// ================= DELETE DOCTOR (SOFT DELETE) =================
	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<APIResponse<Void>> deletePatient(@PathVariable Long doctorId) {
		doctorService.deleteDoctor(doctorId);
		return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
				HttpStatus.OK.value(), "Doctor with ID "+doctorId+" has been deactivated.", null, LocalDateTime.now()));

	}

	// ================= Activate DOCTOR =================
	@PutMapping("/activate/{doctorId}")
	public ResponseEntity<APIResponse<String>> activatePatient(@PathVariable Long doctorId) {
		doctorService.activateDoctor(doctorId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new APIResponse<>(ResponseStatus.SUCCESS.name(), HttpStatus.OK.value(),
						"Doctor activated successfully", "Doctor ID " + doctorId + " is now ACTIVE",
						LocalDateTime.now()));

	}
	
}
