package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.LabInvestigation;

import java.util.List;

@Repository
public interface LabInvestigationRepository extends JpaRepository<LabInvestigation, Long> {

    // Fetch lab reports and requests by patient ID
    List<LabInvestigation> findByPatientIdOrderByRequestedDateDesc(Long patientId);

    // Fetch lab investigations associated with a specific prescription ID
    List<LabInvestigation> findByPrescriptionId(Long prescriptionId);

    // Fetch pending or completed lab reports by status
    List<LabInvestigation> findByPatientIdAndStatus(Long patientId, String status);
}