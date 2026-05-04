package com.HospitalManagementSystem.Service;

import com.HospitalManagementSystem.Enum.PaymentStatus;
import com.HospitalManagementSystem.dto.BillRequestDTO;
import com.HospitalManagementSystem.dto.BillResponseDTO;
import com.HospitalManagementSystem.entity.Bill;

public interface BillService {

	BillResponseDTO  generateBill(BillRequestDTO dto);
	 
	BillResponseDTO updatePaymentStatus(Long billId, PaymentStatus status);
}
