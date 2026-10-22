package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.LabInvestigation;

import java.util.List;

@Repository
public interface LabInvestigationRepository extends JpaRepository<LabInvestigation, Long> {

    List<LabInvestigation> findByPatientIdOrderByRequestedDateDesc(Long patientId);

    List<LabInvestigation> findByPrescriptionId(Long prescriptionId);

    List<LabInvestigation> findByPatientIdAndStatus(Long patientId, String status);
}