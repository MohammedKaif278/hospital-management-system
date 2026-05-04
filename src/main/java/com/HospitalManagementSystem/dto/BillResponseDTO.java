package com.HospitalManagementSystem.dto;

import java.time.LocalDateTime;

import com.HospitalManagementSystem.Enum.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

	private Long billId;
    private Long appointmentId;
   
    private Double consultationFee;
    private PaymentStatus status;
    private LocalDateTime paidAt;
    private LocalDateTime billDate;
}
