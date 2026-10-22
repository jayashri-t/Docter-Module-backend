package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PatientHistoryDTO;
import com.hospital.hospital_management.model.PatientHistory;
import com.hospital.hospital_management.repository.PatientHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientHistoryServiceImpl implements PatientHistoryService {

    private final PatientHistoryRepository repository;

    public PatientHistoryServiceImpl(PatientHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientHistoryDTO saveOrUpdateHistory(PatientHistoryDTO dto) {
        PatientHistory entity = mapToEntity(dto);
        PatientHistory saved = repository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public PatientHistoryDTO getHistoryByPatientId(Long patientId) {
        return repository.findByPatientId(patientId)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<PatientHistoryDTO> getHistoryListByPatientId(Long patientId) {
        return repository.findByPatientIdOrderByDiagnosisDateDesc(patientId).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    private PatientHistoryDTO mapToDTO(PatientHistory entity) {
        PatientHistoryDTO dto = new PatientHistoryDTO();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatientId());
        dto.setMedicalCondition(entity.getMedicalCondition());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setDiagnosisDate(entity.getDiagnosisDate());
        dto.setAllergies(entity.getAllergies());
        return dto;
    }

    private PatientHistory mapToEntity(PatientHistoryDTO dto) {
        PatientHistory entity = new PatientHistory();
        entity.setId(dto.getId());
        entity.setPatientId(dto.getPatientId());
        entity.setMedicalCondition(dto.getMedicalCondition());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setDiagnosisDate(dto.getDiagnosisDate());
        entity.setAllergies(dto.getAllergies());
        return entity;
    }
}