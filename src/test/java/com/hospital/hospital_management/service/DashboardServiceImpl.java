package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.DashboardStatsDTO;
import com.hospital.hospital_management.repository.AppointmentRepository;
import com.hospital.hospital_management.repository.FollowUpRepository;
import com.hospital.hospital_management.service.DashboardService;
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

        // 1. आजच्या सर्व appointments ची संख्या
        long todayAppointments = appointmentRepository.countByDoctorIdAndAppointmentDate(doctorId, today);

        // 2. Pending (SCHEDULED) appointments ची संख्या
        long pendingAppointments = appointmentRepository.countByDoctorIdAndStatus(doctorId, "SCHEDULED");

        // 3. Completed appointments ची संख्या
        long completedAppointments = appointmentRepository.countByDoctorIdAndStatus(doctorId, "COMPLETED");

        // 4. आजच्या प्रलंबित (PENDING) follow-ups ची संख्या
        long pendingFollowUps = followUpRepository.countByDoctorIdAndFollowUpDateAndStatus(doctorId, today, "PENDING");

        return new DashboardStatsDTO(todayAppointments, pendingAppointments, completedAppointments, pendingFollowUps);
    }
}