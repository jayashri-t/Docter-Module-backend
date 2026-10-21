package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PrescriptionDTO;

import java.util.List;

public interface PrescriptionService {
    PrescriptionDTO createPrescription(PrescriptionDTO dto);
    List<PrescriptionDTO> getPrescriptionsByPatient(Long patientId);
    PrescriptionDTO getLatestPrescriptionByPatient(Long patientId);
    List<PrescriptionDTO> getPrescriptionsByDoctorAndPatient(Long doctorId, Long patientId);
}