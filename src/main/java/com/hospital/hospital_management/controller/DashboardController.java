package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.DashboardStatsDTO;
import com.hospital.hospital_management.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173") 
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<DashboardStatsDTO> getDoctorDashboardStats(@PathVariable Long doctorId) {
        DashboardStatsDTO stats = dashboardService.getDoctorDashboardStats(doctorId);
        return ResponseEntity.ok(stats);
    }
}