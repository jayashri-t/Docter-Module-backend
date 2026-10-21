package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.AppointmentDTO;
import com.hospital.hospital_management.dto.DashboardStatsDTO;
import com.hospital.hospital_management.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS
})
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // १. नवीन Appointment तयार करणे (POST)
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentService.createAppointment(dto));
    }

    // २. Doctor साठी Dashboard Stats
    @GetMapping("/doctor/{doctorId}/stats")
    public ResponseEntity<DashboardStatsDTO> getDoctorDashboardStats(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getDoctorDashboardStats(doctorId));
    }

    // ३. आजची Schedule List
    @GetMapping("/doctor/{doctorId}/today-schedule")
    public ResponseEntity<List<AppointmentDTO>> getTodaySchedule(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getTodaySchedule(doctorId));
    }

    // ४. पुढील (Upcoming) Appointments List
    @GetMapping("/doctor/{doctorId}/upcoming")
    public ResponseEntity<List<AppointmentDTO>> getUpcomingAppointments(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getUpcomingAppointments(doctorId));
    }

    // ५. Status नुसार Doctor च्या Appointments फिल्टर करणे (Completed, Cancelled इ.)
    @GetMapping("/doctor/{doctorId}/status")
    public ResponseEntity<List<AppointmentDTO>> getDoctorAppointmentsByStatus(
            @PathVariable Long doctorId,
            @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.getDoctorAppointmentsByStatus(doctorId, status));
    }

    // ६. Care Type नुसार Doctor च्या Appointments फिल्टर करणे (Emergency इ.)
    @GetMapping("/doctor/{doctorId}/care-type")
    public ResponseEntity<List<AppointmentDTO>> getDoctorAppointmentsByCareType(
            @PathVariable Long doctorId,
            @RequestParam String careType) {
        return ResponseEntity.ok(appointmentService.getDoctorAppointmentsByCareType(doctorId, careType));
    }

    // ७. Status अपडेट करण्यासाठी PUT Endpoint (Start Consultation, Complete, Cancel, Reschedule)
    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentDTO> updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, status));
    }

    // Patient साठी असलेले जुने Endpoints
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsForPatient(patientId));
    }

    @GetMapping("/patient/{patientId}/status")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointmentsByStatus(
            @PathVariable Long patientId,
            @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.getPatientAppointmentsByStatus(patientId, status));
    }

    @GetMapping("/patient/{patientId}/date")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointmentsByDate(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.getPatientAppointmentsByDate(patientId, date));
    }
}