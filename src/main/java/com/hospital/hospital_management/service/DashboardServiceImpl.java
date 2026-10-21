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

        // 1. आजच्या सर्व appointments ची संख्या
        long todayAppointments = appointmentRepository.countByDoctorIdAndAppointmentDate(doctorId, today);

        // 2. Pending (SCHEDULED) appointments ची संख्या
        long pendingAppointments = appointmentRepository.countByDoctorIdAndStatus(doctorId, "SCHEDULED");

        // 3. Completed appointments ची संख्या
        long completedAppointments = appointmentRepository.countByDoctorIdAndStatus(doctorId, "COMPLETED");

        // 4. प्रलंबित (PENDING/Scheduled) follow-ups ची संख्या
        long pendingFollowUps = followUpRepository.countByDoctorIdAndStatusIgnoreCase(doctorId, "Pending");
        if (pendingFollowUps == 0) {
            pendingFollowUps = followUpRepository.countByDoctorIdAndStatusIgnoreCase(doctorId, "Scheduled");
        }

        // 5. या डॉक्टरचे एकूण Follow-up Patients
        long totalFollowups = followUpRepository.countByDoctorId(doctorId);

        // 6. Emergency Cases संख्या (reason/notes मध्ये 'emergency' शोधणे)
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