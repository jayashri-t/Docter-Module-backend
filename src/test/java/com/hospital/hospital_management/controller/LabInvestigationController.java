package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.LabInvestigationDTO;
import com.hospital.hospital_management.service.LabInvestigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-investigations")
@CrossOrigin(origins = "http://localhost:5173")
public class LabInvestigationController {

    private final LabInvestigationService service;

    public LabInvestigationController(LabInvestigationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LabInvestigationDTO> createLabInvestigation(@RequestBody LabInvestigationDTO dto) {
        return ResponseEntity.ok(service.createLabInvestigation(dto));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LabInvestigationDTO>> getByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getByPatientId(patientId));
    }

    @GetMapping("/prescription/{prescriptionId}")
    public ResponseEntity<List<LabInvestigationDTO>> getByPrescriptionId(@PathVariable Long prescriptionId) {
        return ResponseEntity.ok(service.getByPrescriptionId(prescriptionId));
    }

    @GetMapping("/patient/{patientId}/status")
    public ResponseEntity<List<LabInvestigationDTO>> getByPatientIdAndStatus(
            @PathVariable Long patientId,
            @RequestParam String status) {
        return ResponseEntity.ok(service.getByPatientIdAndStatus(patientId, status));
    }
}