package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.LabInvestigationDTO;
import com.hospital.hospital_management.model.LabInvestigation;
import com.hospital.hospital_management.repository.LabInvestigationRepository;
import com.hospital.hospital_management.service.LabInvestigationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabInvestigationServiceImpl implements LabInvestigationService {

    private final LabInvestigationRepository repository;

    public LabInvestigationServiceImpl(LabInvestigationRepository repository) {
        this.repository = repository;
    }

    @Override
    public LabInvestigationDTO createLabInvestigation(LabInvestigationDTO dto) {
        LabInvestigation entity = mapToEntity(dto);
        LabInvestigation saved = repository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<LabInvestigationDTO> getByPatientId(Long patientId) {
        return repository.findByPatientIdOrderByRequestedDateDesc(patientId).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LabInvestigationDTO> getByPrescriptionId(Long prescriptionId) {
        return repository.findByPrescriptionId(prescriptionId).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LabInvestigationDTO> getByPatientIdAndStatus(Long patientId, String status) {
        return repository.findByPatientIdAndStatus(patientId, status).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    private LabInvestigationDTO mapToDTO(LabInvestigation entity) {
        LabInvestigationDTO dto = new LabInvestigationDTO();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatientId());
        dto.setPrescriptionId(entity.getPrescriptionId());
        dto.setTestName(entity.getTestName());
        dto.setStatus(entity.getStatus());
        dto.setRequestedDate(entity.getRequestedDate());
        dto.setResultDetails(entity.getResultDetails());
        return dto;
    }

    private LabInvestigation mapToEntity(LabInvestigationDTO dto) {
        LabInvestigation entity = new LabInvestigation();
        entity.setId(dto.getId());
        entity.setPatientId(dto.getPatientId());
        entity.setPrescriptionId(dto.getPrescriptionId());
        entity.setTestName(dto.getTestName());
        entity.setStatus(dto.getStatus());
        entity.setRequestedDate(dto.getRequestedDate());
        entity.setResultDetails(dto.getResultDetails());
        return entity;
    }
}