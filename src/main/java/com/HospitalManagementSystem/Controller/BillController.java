package com.HospitalManagementSystem.Controller;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagementSystem.Enum.ResponseStatus;
import com.HospitalManagementSystem.Service.BillService;
import com.HospitalManagementSystem.dto.BillRequestDTO;
import com.HospitalManagementSystem.dto.BillResponseDTO;
import com.HospitalManagementSystem.dto.PaymentStatusRequestDTO;
import com.HospitalManagementSystem.payload.APIResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/hms/bills")
@RequiredArgsConstructor
public class BillController {

	private final BillService billService;
	
	 @PostMapping("/generate")
	    public ResponseEntity<APIResponse<?>> generateBill(@RequestBody BillRequestDTO dto) {
	        BillResponseDTO bill = billService.generateBill(dto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(ResponseStatus.SUCCESS.name(),
					HttpStatus.CREATED.value(), "Bill Generated successfully", bill, LocalDateTime.now()));
		
	    }

	
	 @PutMapping("/payment-status/{billId}")
	    public ResponseEntity<APIResponse<BillResponseDTO>> updatePaymentStatus(
	            @PathVariable Long billId,
	            @RequestBody PaymentStatusRequestDTO dto) {

	        BillResponseDTO updatedBill = billService.updatePaymentStatus(billId, dto.getPaymentStatus());

	        return ResponseEntity.ok(
	            new APIResponse<>(
	                ResponseStatus.SUCCESS.name(),
	                HttpStatus.OK.value(),
	                "Payment status updated successfully",
	                updatedBill,
	                LocalDateTime.now()
	            )
	        );
	 }
}
