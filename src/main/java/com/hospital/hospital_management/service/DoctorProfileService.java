package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.DoctorProfileDTO;

public interface DoctorProfileService {
    DoctorProfileDTO createOrUpdateProfile(DoctorProfileDTO dto);
    DoctorProfileDTO getById(Long id);
    DoctorProfileDTO getByRegistrationNumber(String regNo);
    DoctorProfileDTO getByEmail(String email);
}