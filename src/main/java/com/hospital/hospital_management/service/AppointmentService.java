package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.AppointmentDTO;
import com.hospital.hospital_management.dto.DashboardStatsDTO;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    
    // CRUD Operations
    AppointmentDTO createAppointment(AppointmentDTO dto);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(Long id);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO dto);
    void deleteAppointment(Long id);

    // Doctor Dashboard & Schedule
    DashboardStatsDTO getDoctorDashboardStats(Long doctorId);
    List<AppointmentDTO> getTodaySchedule(Long doctorId);
    List<AppointmentDTO> getUpcomingAppointments(Long doctorId);

    // Patient Queries
    List<AppointmentDTO> getAppointmentsByPatientId(Long patientId);
    List<AppointmentDTO> getPatientAppointmentsByStatus(Long patientId, String status);
    List<AppointmentDTO> getPatientAppointmentsByDate(Long patientId, LocalDate date);
    List<AppointmentDTO> getAllAppointmentsForPatient(Long patientId);
    
    // Status & Care Type Updates
    AppointmentDTO updateCareType(Long appointmentId, String careType);
    AppointmentDTO updateStatus(Long appointmentId, String status);
}