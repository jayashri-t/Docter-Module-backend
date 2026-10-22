package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.PatientDTO;
import com.hospital.hospital_management.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.createPatient(patientDTO));
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO dto = patientService.getPatientById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/registration/{regNo}")
    public ResponseEntity<PatientDTO> getPatientByRegistrationNumber(@PathVariable String regNo) {
        PatientDTO dto = patientService.getPatientByRegistrationNumber(regNo);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}