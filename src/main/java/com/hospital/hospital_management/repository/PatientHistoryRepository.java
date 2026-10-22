package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.PatientHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, Long> {

    Optional<PatientHistory> findByPatientId(Long patientId);

    List<PatientHistory> findByPatientIdOrderByDiagnosisDateDesc(Long patientId);
}