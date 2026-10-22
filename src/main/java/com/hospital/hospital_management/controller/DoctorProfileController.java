package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.DoctorProfileDTO;
import com.hospital.hospital_management.service.DoctorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/doctors", "/api/doctor/profile"}) 
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorProfileController {

    private final DoctorProfileService service;

    public DoctorProfileController(DoctorProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DoctorProfileDTO> createOrUpdate(@RequestBody DoctorProfileDTO dto) {
        return ResponseEntity.ok(service.createOrUpdateProfile(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileDTO> getById(@PathVariable Long id) {
        DoctorProfileDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/registration/{regNo}")
    public ResponseEntity<DoctorProfileDTO> getByRegistrationNumber(@PathVariable String regNo) {
        DoctorProfileDTO dto = service.getByRegistrationNumber(regNo);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email")
    public ResponseEntity<DoctorProfileDTO> getByEmail(@RequestParam String email) {
        DoctorProfileDTO dto = service.getByEmail(email);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}