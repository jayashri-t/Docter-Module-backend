package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.AppointmentDTO;
import com.hospital.hospital_management.dto.DashboardStatsDTO;
import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.model.User;
import com.hospital.hospital_management.repository.AppointmentRepository;
import com.hospital.hospital_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment entity = mapToEntity(dto);
        Appointment saved = appointmentRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
        return mapToDTO(appointment);
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));

        Long previousPatientId = existing.getPatientId();
        
        // Safeguard for doctorId
        existing.setDoctorId(dto.getDoctorId() != null ? dto.getDoctorId() : (existing.getDoctorId() != null ? existing.getDoctorId() : 1L));
        
        existing.setPatientId(dto.getPatientId());
        existing.setPatientName(dto.getPatientName());
        if (hasText(dto.getContact())) {
            existing.setContact(dto.getContact());
        } else if (!Objects.equals(previousPatientId, dto.getPatientId())) {
            existing.setContact(null);
        }
        existing.setAge(dto.getAge());
        existing.setGender(dto.getGender());
        // Fallback to today's date if appointmentDate from DTO is null
        existing.setAppointmentDate(dto.getAppointmentDate() != null ? dto.getAppointmentDate() : LocalDate.now());
        existing.setAppointmentTime(dto.getAppointmentTime());
        existing.setAppointmentDateTime(dto.getAppointmentDateTime());
        existing.setStatus(dto.getStatus());
        existing.setCareType(dto.getCareType());
        existing.setReason(dto.getReason());
        existing.setReferredBy(dto.getReferredBy());
        existing.setTokenNumber(dto.getTokenNumber());

        Appointment updated = appointmentRepository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. Appointment not found with ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<AppointmentDTO> getTodaySchedule(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, LocalDate.now())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getUpcomingAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateAfter(doctorId, LocalDate.now())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateCareType(Long appointmentId, String careType) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));
        appointment.setCareType(careType);
        Appointment updated = appointmentRepository.save(appointment);
        return mapToDTO(updated);
    }

    @Override
    public AppointmentDTO updateStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));
        appointment.setStatus(status);
        Appointment updated = appointmentRepository.save(appointment);
        return mapToDTO(updated);
    }

    @Override
    public DashboardStatsDTO getDoctorDashboardStats(Long doctorId) {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setTodayAppointments(appointmentRepository.countByDoctorIdAndAppointmentDate(doctorId, LocalDate.now()));
        stats.setCompletedAppointments(appointmentRepository.countByDoctorIdAndStatus(doctorId, "COMPLETED"));
        stats.setPendingAppointments(appointmentRepository.countByDoctorIdAndStatus(doctorId, "SCHEDULED"));
        stats.setPendingFollowUps(0);
        return stats;
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getPatientAppointmentsByStatus(Long patientId, String status) {
        return appointmentRepository.findByPatientIdAndStatus(patientId, status)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getPatientAppointmentsByDate(Long patientId, LocalDate date) {
        return appointmentRepository.findByPatientIdAndAppointmentDate(patientId, date)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsForPatient(Long patientId) {
        return getAppointmentsByPatientId(patientId);
    }

    private AppointmentDTO mapToDTO(Appointment entity) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(entity.getId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setPatientId(entity.getPatientId());
        String patientName = entity.getPatientName();
        String contact = entity.getContact();
        if ((!hasText(patientName) || !hasText(contact)) && entity.getPatientId() != null) {
            User patient = userRepository.findById(entity.getPatientId()).orElse(null);
            if (patient != null) {
                if (!hasText(patientName)) {
                    patientName = patient.getFullName();
                }
                if (!hasText(contact)) {
                    contact = patient.getPhoneNumber();
                }
            }
        }
        dto.setPatientName(patientName);
        dto.setContact(contact);
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setAppointmentDate(entity.getAppointmentDate());
        dto.setAppointmentTime(entity.getAppointmentTime());
        dto.setAppointmentDateTime(entity.getAppointmentDateTime());
        dto.setStatus(entity.getStatus());
        dto.setCareType(entity.getCareType());
        dto.setReason(entity.getReason());
        dto.setReferredBy(entity.getReferredBy());
        dto.setTokenNumber(entity.getTokenNumber());
        return dto;
    }

    private boolean hasText(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        String normalized = value.trim();
        return !normalized.equalsIgnoreCase("n/a")
                && !normalized.equalsIgnoreCase("na")
                && !normalized.equalsIgnoreCase("null");
    }

    private Appointment mapToEntity(AppointmentDTO dto) {
        Appointment entity = new Appointment();
        entity.setId(dto.getId());
        
        // Safeguard: if doctorId is null, default to 1L to satisfy database NOT NULL constraint
        entity.setDoctorId(dto.getDoctorId() != null ? dto.getDoctorId() : 1L);
        
        entity.setPatientId(dto.getPatientId());
        entity.setPatientName(dto.getPatientName());
        entity.setContact(dto.getContact());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        
        // Safeguard: if appointmentDate is null, default to LocalDate.now() to satisfy database NOT NULL constraint
        entity.setAppointmentDate(dto.getAppointmentDate() != null ? dto.getAppointmentDate() : LocalDate.now());
        
        entity.setAppointmentTime(dto.getAppointmentTime());
        entity.setAppointmentDateTime(dto.getAppointmentDateTime());
        entity.setStatus(dto.getStatus());
        entity.setCareType(dto.getCareType());
        entity.setReason(dto.getReason());
        entity.setReferredBy(dto.getReferredBy());
        entity.setTokenNumber(dto.getTokenNumber());
        return entity;
    }
}