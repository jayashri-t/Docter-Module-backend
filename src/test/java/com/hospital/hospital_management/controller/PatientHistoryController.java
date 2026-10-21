package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.PatientHistoryDTO;
import com.hospital.hospital_management.service.PatientHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-history")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientHistoryController {

    private final PatientHistoryService service;

    public PatientHistoryController(PatientHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PatientHistoryDTO> saveOrUpdate(@RequestBody PatientHistoryDTO dto) {
        return ResponseEntity.ok(service.saveOrUpdateHistory(dto));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<PatientHistoryDTO> getHistoryByPatientId(@PathVariable Long patientId) {
        PatientHistoryDTO dto = service.getHistoryByPatientId(patientId);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/patient/{patientId}/all")
    public ResponseEntity<List<PatientHistoryDTO>> getHistoryListByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getHistoryListByPatientId(patientId));
    }
}