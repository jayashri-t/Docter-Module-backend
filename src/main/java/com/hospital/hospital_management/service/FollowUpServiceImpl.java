package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.FollowUpDTO;
import com.hospital.hospital_management.model.FollowUp;
import com.hospital.hospital_management.repository.FollowUpRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowUpServiceImpl implements FollowUpService {

    private final FollowUpRepository repository;

    public FollowUpServiceImpl(FollowUpRepository repository) {
        this.repository = repository;
    }

    @Override
    public FollowUpDTO createFollowUp(FollowUpDTO dto) {
        FollowUp followUp = mapToEntity(dto);
        FollowUp saved = repository.save(followUp);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FollowUpDTO> getFollowUpsByDoctorAndDate(Long doctorId, LocalDate date) {
        return repository.findByDoctorIdAndFollowUpDate(doctorId, date).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FollowUpDTO> getFollowUpsByPatient(Long patientId) {
        return repository.findByPatientIdOrderByFollowUpDateDesc(patientId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FollowUpDTO> getFollowUpsByPrescription(Long prescriptionId) {
        return repository.findByPrescriptionId(prescriptionId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long getPendingFollowUpCountByDoctor(Long doctorId, String status) {
        return repository.countByDoctorIdAndStatusIgnoreCase(doctorId, status);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalFollowUpsByDoctor(Long doctorId) {
        return repository.countByDoctorId(doctorId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getEmergencyCountByDoctor(Long doctorId, String keyword) {
        return repository.countByDoctorIdAndReasonOrNotesContaining(doctorId, keyword);
    }

    private FollowUpDTO mapToDTO(FollowUp entity) {
        if (entity == null) {
            return null;
        }
        FollowUpDTO dto = new FollowUpDTO();
        dto.setId(entity.getId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setPatientId(entity.getPatientId());
        dto.setPrescriptionId(entity.getPrescriptionId());
        dto.setFollowUpDate(entity.getFollowUpDate());
        dto.setStatus(entity.getStatus());
        dto.setReason(entity.getReason());
        dto.setNotes(entity.getNotes());
        return dto;
    }

    private FollowUp mapToEntity(FollowUpDTO dto) {
        if (dto == null) {
            return null;
        }
        FollowUp entity = new FollowUp();
        entity.setId(dto.getId());
        entity.setDoctorId(dto.getDoctorId());
        entity.setPatientId(dto.getPatientId());
        entity.setPrescriptionId(dto.getPrescriptionId());
        entity.setFollowUpDate(dto.getFollowUpDate());
        entity.setStatus(dto.getStatus());
        entity.setReason(dto.getReason());
        entity.setNotes(dto.getNotes());
        return entity;
    }
}