package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PatientHistoryDTO;

import java.util.List;

public interface PatientHistoryService {
    PatientHistoryDTO saveOrUpdateHistory(PatientHistoryDTO dto);
    PatientHistoryDTO getHistoryByPatientId(Long patientId);
    List<PatientHistoryDTO> getHistoryListByPatientId(Long patientId);
}