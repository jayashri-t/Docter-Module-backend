package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.FollowUp;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    // Fetch follow-ups for a doctor scheduled for a specific date
    List<FollowUp> findByDoctorIdAndFollowUpDate(Long doctorId, LocalDate date);

    // Fetch follow-ups for a specific patient
    List<FollowUp> findByPatientIdOrderByFollowUpDateDesc(Long patientId);

    // Count pending follow-ups for doctor dashboard card
    long countByDoctorIdAndFollowUpDateAndStatus(Long doctorId, LocalDate date, String status);

    // Fetch follow-ups linked to a specific prescription
    List<FollowUp> findByPrescriptionId(Long prescriptionId);
}