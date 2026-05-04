package com.HospitalManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagementSystem.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

	boolean existsByAppointment_AppointmentId(Long appointmentId);

    Bill findByAppointment_AppointmentId(Long appointmentId);
}
