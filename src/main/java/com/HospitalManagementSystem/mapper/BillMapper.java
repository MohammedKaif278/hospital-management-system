package com.HospitalManagementSystem.mapper;

import com.HospitalManagementSystem.dto.BillResponseDTO;
import com.HospitalManagementSystem.entity.Bill;

public class BillMapper {

	 private BillMapper() {
	        // Prevent object creation
	    }

	    public static BillResponseDTO toDTO(Bill bill) {

	        BillResponseDTO dto = new BillResponseDTO();

	        dto.setBillId(bill.getBillId());
	        dto.setAppointmentId(bill.getAppointment().getAppointmentId());
	        dto.setConsultationFee(bill.getConsultationFee());
	        dto.setStatus(bill.getPaymentStatus());
	        dto.setBillDate(bill.getBillDate());
	        dto.setPaidAt(bill.getPaidAt());
	        return dto;
	    }
	
}
