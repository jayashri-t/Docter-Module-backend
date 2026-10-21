package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.PrescriptionDTO;
import com.hospital.hospital_management.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "http://localhost:5173")
public class PrescriptionController {

    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@RequestBody PrescriptionDTO dto) {
        return ResponseEntity.ok(service.createPrescription(dto));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getPrescriptionsByPatient(patientId));
    }

    @GetMapping("/patient/{patientId}/latest")
    public ResponseEntity<PrescriptionDTO> getLatestPrescriptionByPatient(@PathVariable Long patientId) {
        PrescriptionDTO dto = service.getLatestPrescriptionByPatient(patientId);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/doctor/{doctorId}/patient/{patientId}")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByDoctorAndPatient(
            @PathVariable Long doctorId,
            @PathVariable Long patientId) {
        return ResponseEntity.ok(service.getPrescriptionsByDoctorAndPatient(doctorId, patientId));
    }
}