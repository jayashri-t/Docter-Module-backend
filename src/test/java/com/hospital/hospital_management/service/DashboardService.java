package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.DashboardStatsDTO;

public interface DashboardService {
    DashboardStatsDTO getDoctorDashboardStats(Long doctorId);
}