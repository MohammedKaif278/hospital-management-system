package com.HospitalManagementSystem.ServiceImplementation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.Enum.AppointmentStatus;
import com.HospitalManagementSystem.Enum.PaymentStatus;
import com.HospitalManagementSystem.Repository.AppointmentRepository;
import com.HospitalManagementSystem.Repository.BillRepository;
import com.HospitalManagementSystem.Service.BillService;
import com.HospitalManagementSystem.dto.BillRequestDTO;
import com.HospitalManagementSystem.dto.BillResponseDTO;
import com.HospitalManagementSystem.entity.Appointment;
import com.HospitalManagementSystem.entity.Bill;
import com.HospitalManagementSystem.exception.ResourceNotFoundException;
import com.HospitalManagementSystem.mapper.BillMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

	private final AppointmentRepository appointmentRepository;
	private final BillRepository billRepository;

	@Override
	public BillResponseDTO  generateBill(BillRequestDTO dto) {
		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
				.orElseThrow(() -> new RuntimeException("Appoinment Not Found"));

		if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
			throw new RuntimeException("Bill can be generated only after appointment completion");
		}

		if (billRepository.existsByAppointment_AppointmentId(dto.getAppointmentId())) {
			throw new RuntimeException("Bill already generated for this appointment");
		}

		

		
		Bill bill=new Bill();
		bill.setAppointment(appointment);
		
		bill.setPaymentStatus(PaymentStatus.PENDING);
		bill.setBillDate(LocalDateTime.now());
		Bill save = billRepository.save(bill);
		
		return BillMapper.toDTO(save);
	}

	
	
	
	@Override
	public BillResponseDTO updatePaymentStatus(Long billId, PaymentStatus newStatus) {

	    if (newStatus == null) {
	        throw new IllegalArgumentException("Payment status must not be null");
	    }

	    Bill bill = billRepository.findById(billId)
	        .orElseThrow(() -> new ResourceNotFoundException(
	            "Bill not found with this Bill ID :- " + billId));

	    if (bill.getPaymentStatus() == PaymentStatus.PAID) {
	        throw new IllegalStateException("Payment already completed");
	    }

	    bill.setPaymentStatus(newStatus);

	    if (newStatus == PaymentStatus.PAID) {
	        bill.setPaidAt(LocalDateTime.now());
	    }

	    Bill saved = billRepository.save(bill);

	    return BillMapper.toDTO(saved);
	}


}
