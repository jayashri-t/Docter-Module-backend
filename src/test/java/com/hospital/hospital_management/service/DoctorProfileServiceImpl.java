package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.DoctorProfileDTO;
import com.hospital.hospital_management.model.DoctorProfile;
import com.hospital.hospital_management.repository.DoctorProfileRepository;
import com.hospital.hospital_management.service.DoctorProfileService;
import org.springframework.stereotype.Service;

@Service
public class DoctorProfileServiceImpl implements DoctorProfileService {

    private final DoctorProfileRepository repository;

    public DoctorProfileServiceImpl(DoctorProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public DoctorProfileDTO createOrUpdateProfile(DoctorProfileDTO dto) {
        DoctorProfile profile = mapToEntity(dto);
        DoctorProfile saved = repository.save(profile);
        return mapToDTO(saved);
    }

    @Override
    public DoctorProfileDTO getByRegistrationNumber(String regNo) {
        return repository.findByRegistrationNumber(regNo)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public DoctorProfileDTO getByEmail(String email) {
        return repository.findByEmail(email)
                .map(this::mapToDTO)
                .orElse(null);
    }

    private DoctorProfileDTO mapToDTO(DoctorProfile entity) {
        DoctorProfileDTO dto = new DoctorProfileDTO();
        dto.setId(entity.getId());
        dto.setRegistrationNumber(entity.getRegistrationNumber());
        dto.setEmail(entity.getEmail());
        dto.setFullName(entity.getFullName());
        dto.setSpecialization(entity.getSpecialization());
        return dto;
    }

    private DoctorProfile mapToEntity(DoctorProfileDTO dto) {
        DoctorProfile entity = new DoctorProfile();
        entity.setId(dto.getId());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setSpecialization(dto.getSpecialization());
        return entity;
    }
}