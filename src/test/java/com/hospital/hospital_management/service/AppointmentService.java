package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.AppointmentDTO;
import com.hospital.hospital_management.dto.DashboardStatsDTO;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO dto);
    DashboardStatsDTO getDoctorDashboardStats(Long doctorId);
    List<AppointmentDTO> getTodaySchedule(Long doctorId);
    List<AppointmentDTO> getUpcomingAppointments(Long doctorId);
    
    // नवीन जोडलेल्या पद्धती (Methods)
    List<AppointmentDTO> getDoctorAppointmentsByStatus(Long doctorId, String status);
    List<AppointmentDTO> getDoctorAppointmentsByCareType(Long doctorId, String careType);
    AppointmentDTO updateAppointmentStatus(Long id, String status);

    List<AppointmentDTO> getPatientAppointmentsByStatus(Long patientId, String status);
    List<AppointmentDTO> getPatientAppointmentsByDate(Long patientId, LocalDate date);
    List<AppointmentDTO> getAllAppointmentsForPatient(Long patientId);
}