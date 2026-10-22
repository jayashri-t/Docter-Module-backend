package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.PrescriptionDTO;
import com.hospital.hospital_management.model.Prescription;
import com.hospital.hospital_management.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    public PrescriptionServiceImpl(PrescriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public PrescriptionDTO createPrescription(PrescriptionDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Prescription request payload cannot be null.");
        }
        if (dto.getDoctorId() == null) {
            throw new IllegalArgumentException("Doctor ID is required and cannot be null.");
        }
        if (dto.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID is required and cannot be null.");
        }

        Prescription entity = mapToEntity(dto);

        if (entity.getPrescriptionDate() == null) {
            entity.setPrescriptionDate(LocalDateTime.now());
        }

        Prescription saved = repository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionsByPatient(Long patientId) {
        return repository.findByPatientIdOrderByPrescriptionDateDesc(patientId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDTO getLatestPrescriptionByPatient(Long patientId) {
        return repository.findFirstByPatientIdOrderByPrescriptionDateDesc(patientId)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionsByDoctorAndPatient(Long doctorId, Long patientId) {
        return repository.findByDoctorIdAndPatientId(doctorId, patientId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PrescriptionDTO mapToDTO(Prescription entity) {
        if (entity == null) {
            return null;
        }
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setId(entity.getId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setPatientId(entity.getPatientId());
        dto.setInstructions(entity.getInstructions());
        dto.setPrescriptionDate(entity.getPrescriptionDate());
        return dto;
    }

    private Prescription mapToEntity(PrescriptionDTO dto) {
        Prescription entity = new Prescription();
        entity.setId(dto.getId());
        entity.setDoctorId(dto.getDoctorId());
        entity.setPatientId(dto.getPatientId());
        entity.setInstructions(dto.getInstructions());
        entity.setPrescriptionDate(dto.getPrescriptionDate());
        return entity;
    }
}