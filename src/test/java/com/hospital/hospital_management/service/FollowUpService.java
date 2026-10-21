package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.FollowUpDTO;

import java.time.LocalDate;
import java.util.List;

public interface FollowUpService {
    FollowUpDTO createFollowUp(FollowUpDTO dto);
    List<FollowUpDTO> getFollowUpsByDoctorAndDate(Long doctorId, LocalDate date);
    List<FollowUpDTO> getFollowUpsByPatient(Long patientId);
    List<FollowUpDTO> getFollowUpsByPrescription(Long prescriptionId);
}