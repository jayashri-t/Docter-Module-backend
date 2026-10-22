package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.DoctorProfileDTO;
import com.hospital.hospital_management.model.DoctorProfile;
import com.hospital.hospital_management.repository.DoctorProfileRepository;
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
    public DoctorProfileDTO getById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
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
        dto.setAvailable(entity.getAvailable());
        dto.setConsultationFee(entity.getConsultationFee());
        dto.setExperienceYears(entity.getExperienceYears());
        dto.setQualification(entity.getQualification());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setUserId(entity.getUserId());
        return dto;
    }

    private DoctorProfile mapToEntity(DoctorProfileDTO dto) {
        DoctorProfile entity = new DoctorProfile();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setSpecialization(dto.getSpecialization());
        entity.setAvailable(dto.getAvailable());
        entity.setConsultationFee(dto.getConsultationFee());
        entity.setExperienceYears(dto.getExperienceYears());
        entity.setQualification(dto.getQualification());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setUserId(dto.getUserId());
        return entity;
    }
}