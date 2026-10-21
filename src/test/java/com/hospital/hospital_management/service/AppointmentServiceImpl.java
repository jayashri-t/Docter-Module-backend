package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.AppointmentDTO;
import com.hospital.hospital_management.dto.DashboardStatsDTO;
import com.hospital.hospital_management.model.Appointment;
import com.hospital.hospital_management.repository.AppointmentRepository;
import com.hospital.hospital_management.repository.FollowUpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final FollowUpRepository followUpRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, FollowUpRepository followUpRepository) {
        this.appointmentRepository = appointmentRepository;
        this.followUpRepository = followUpRepository;
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment entity = mapToEntity(dto);
        Appointment saved = appointmentRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public DashboardStatsDTO getDoctorDashboardStats(Long doctorId) {
        LocalDate today = LocalDate.now();
        long todayCount = appointmentRepository.countByDoctorIdAndAppointmentDate(doctorId, today);
        long pendingCount = appointmentRepository.countByDoctorIdAndStatus(doctorId, "SCHEDULED");
        long completedCount = appointmentRepository.countByDoctorIdAndStatus(doctorId, "COMPLETED");
        long pendingFollowUps = followUpRepository.countByDoctorIdAndFollowUpDateAndStatus(doctorId, today, "PENDING");

        return new DashboardStatsDTO(todayCount, pendingCount, completedCount, pendingFollowUps);
    }

    @Override
    public List<AppointmentDTO> getTodaySchedule(Long doctorId) {
        List<Appointment> list = appointmentRepository.findByDoctorIdAndAppointmentDateOrderByAppointmentTimeAsc(doctorId, LocalDate.now());
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getUpcomingAppointments(Long doctorId) {
        List<Appointment> list = appointmentRepository.findByDoctorIdAndAppointmentDateTimeAfterOrderByAppointmentDateTimeAsc(doctorId, LocalDateTime.now());
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getDoctorAppointmentsByStatus(Long doctorId, String status) {
        List<Appointment> list = appointmentRepository.findByDoctorIdAndStatus(doctorId, status);
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getDoctorAppointmentsByCareType(Long doctorId, String careType) {
        List<Appointment> list = appointmentRepository.findByDoctorIdAndCareType(doctorId, careType);
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateAppointmentStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        appointment.setStatus(status);
        Appointment updated = appointmentRepository.save(appointment);
        return mapToDTO(updated);
    }

    @Override
    public List<AppointmentDTO> getPatientAppointmentsByStatus(Long patientId, String status) {
        List<Appointment> list = appointmentRepository.findByPatientIdAndStatus(patientId, status);
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getPatientAppointmentsByDate(Long patientId, LocalDate date) {
        List<Appointment> list = appointmentRepository.findByPatientIdAndAppointmentDate(patientId, date);
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsForPatient(Long patientId) {
        List<Appointment> list = appointmentRepository.findByPatientIdOrderByAppointmentDateDesc(patientId);
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private AppointmentDTO mapToDTO(Appointment entity) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(entity.getId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setPatientId(entity.getPatientId());
        dto.setAppointmentDate(entity.getAppointmentDate());
        dto.setAppointmentTime(entity.getAppointmentTime());
        dto.setAppointmentDateTime(entity.getAppointmentDateTime());
        dto.setStatus(entity.getStatus());
        dto.setCareType(entity.getCareType());
        return dto;
    }

    private Appointment mapToEntity(AppointmentDTO dto) {
        Appointment entity = new Appointment();
        entity.setId(dto.getId());
        entity.setDoctorId(dto.getDoctorId());
        entity.setPatientId(dto.getPatientId());
        entity.setAppointmentDate(dto.getAppointmentDate());
        entity.setAppointmentTime(dto.getAppointmentTime());
        entity.setAppointmentDateTime(dto.getAppointmentDateTime());
        entity.setStatus(dto.getStatus());
        entity.setCareType(dto.getCareType());
        return entity;
    }
}