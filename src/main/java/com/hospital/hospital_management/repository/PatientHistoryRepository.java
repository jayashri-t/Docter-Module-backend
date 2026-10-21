package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.PatientHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, Long> {

    // १. पेशंट आयडीवरून हिस्टरी शोधण्यासाठी (हा मेथड मिसिंग होता)
    Optional<PatientHistory> findByPatientId(Long patientId);

    // २. पेशंटच्या सर्व जुन्या हिस्टरीच्या लिस्टसाठी (तारीखेनुसार उरत्या क्रमाने)
    List<PatientHistory> findByPatientIdOrderByDiagnosisDateDesc(Long patientId);
}