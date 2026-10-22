package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.DashboardStatsDTO;
import com.hospital.hospital_management.repository.AppointmentRepository;
import com.hospital.hospital_management.repository.FollowUpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final AppointmentRepository appointmentRepository;
    private final FollowUpRepository followUpRepository;

    public DashboardServiceImpl(AppointmentRepository appointmentRepository, FollowUpRepository followUpRepository) {
        this.appointmentRepository = appointmentRepository;
        this.followUpRepository = followUpRepository;
    }

    @Override
    public DashboardStatsDTO getDoctorDashboardStats(Long doctorId) {
        LocalDate today = LocalDate.now();

        long todayAppointments = appointmentRepository.countByDoctorIdAndAppointmentDate(doctorId, today);
        long pendingAppointments = appointmentRepository.countByDoctorIdAndStatus(doctorId, "SCHEDULED");
        long completedAppointments = appointmentRepository.countByDoctorIdAndStatus(doctorId, "COMPLETED");

        long pendingFollowUps = followUpRepository.countByDoctorIdAndStatusIgnoreCase(doctorId, "Pending");
        if (pendingFollowUps == 0) {
            pendingFollowUps = followUpRepository.countByDoctorIdAndStatusIgnoreCase(doctorId, "Scheduled");
        }

        long totalFollowups = followUpRepository.countByDoctorId(doctorId);
        long emergencyCount = followUpRepository.countByDoctorIdAndReasonOrNotesContaining(doctorId, "emergency");

        return new DashboardStatsDTO(
            todayAppointments, 
            pendingAppointments, 
            completedAppointments, 
            pendingFollowUps, 
            totalFollowups, 
            emergencyCount
        );
    }
}