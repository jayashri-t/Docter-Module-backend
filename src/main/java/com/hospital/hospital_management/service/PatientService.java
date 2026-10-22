package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PatientDTO;
import java.util.List;

public interface PatientService {
    PatientDTO createPatient(PatientDTO dto);
    List<PatientDTO> getAllPatients();
    PatientDTO getPatientById(Long id);
    PatientDTO getPatientByRegistrationNumber(String registrationNumber);
}