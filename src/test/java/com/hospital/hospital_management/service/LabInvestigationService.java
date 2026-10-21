package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.LabInvestigationDTO;

import java.util.List;

public interface LabInvestigationService {
    LabInvestigationDTO createLabInvestigation(LabInvestigationDTO dto);
    List<LabInvestigationDTO> getByPatientId(Long patientId);
    List<LabInvestigationDTO> getByPrescriptionId(Long prescriptionId);
    List<LabInvestigationDTO> getByPatientIdAndStatus(Long patientId, String status);
}