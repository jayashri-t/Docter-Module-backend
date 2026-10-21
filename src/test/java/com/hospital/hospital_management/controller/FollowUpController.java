package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.FollowUpDTO;
import com.hospital.hospital_management.service.FollowUpService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/follow-ups")
@CrossOrigin(origins = "http://localhost:5173")
public class FollowUpController {

    private final FollowUpService service;

    public FollowUpController(FollowUpService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FollowUpDTO> createFollowUp(@RequestBody FollowUpDTO dto) {
        return ResponseEntity.ok(service.createFollowUp(dto));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<FollowUpDTO>> getFollowUpsByDoctorAndDate(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(service.getFollowUpsByDoctorAndDate(doctorId, date));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<FollowUpDTO>> getFollowUpsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getFollowUpsByPatient(patientId));
    }

    @GetMapping("/prescription/{prescriptionId}")
    public ResponseEntity<List<FollowUpDTO>> getFollowUpsByPrescription(@PathVariable Long prescriptionId) {
        return ResponseEntity.ok(service.getFollowUpsByPrescription(prescriptionId));
    }
}