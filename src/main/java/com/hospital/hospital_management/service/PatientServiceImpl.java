package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PatientDTO;
import com.hospital.hospital_management.model.Patient;
import com.hospital.hospital_management.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient entity = mapToEntity(dto);
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now());
        }
        Patient saved = patientRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public PatientDTO getPatientByRegistrationNumber(String registrationNumber) {
        return patientRepository.findByRegistrationNumber(registrationNumber)
                .map(this::mapToDTO)
                .orElse(null);
    }

    private PatientDTO mapToDTO(Patient entity) {
        if (entity == null) return null;
        PatientDTO dto = new PatientDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setBloodGroup(entity.getBloodGroup());
        dto.setEmergencyContact(entity.getEmergencyContact());
        dto.setRegistrationNumber(entity.getRegistrationNumber());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private Patient mapToEntity(PatientDTO dto) {
        if (dto == null) return null;
        Patient entity = new Patient();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setBloodGroup(dto.getBloodGroup());
        entity.setEmergencyContact(dto.getEmergencyContact());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}