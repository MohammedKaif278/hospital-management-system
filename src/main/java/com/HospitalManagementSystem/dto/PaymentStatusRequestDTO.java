package com.HospitalManagementSystem.dto;

import com.HospitalManagementSystem.Enum.PaymentStatus;

import lombok.Data;

@Data
public class PaymentStatusRequestDTO {
    private PaymentStatus paymentStatus;
}
