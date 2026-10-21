package com.hospital.hospital_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "prescription_date", nullable = false, updatable = false)
    private LocalDateTime prescriptionDate;

    public Prescription() {}

    public Prescription(Long doctorId, Long patientId, String instructions) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.instructions = instructions;
        this.prescriptionDate = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.prescriptionDate == null) {
            this.prescriptionDate = LocalDateTime.now();
        }
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public Long getDoctorId() { 
        return doctorId; 
    }
    
    public void setDoctorId(Long doctorId) { 
        this.doctorId = doctorId; 
    }

    public Long getPatientId() { 
        return patientId; 
    }
    
    public void setPatientId(Long patientId) { 
        this.patientId = patientId; 
    }

    public String getInstructions() { 
        return instructions; 
    }
    
    public void setInstructions(String instructions) { 
        this.instructions = instructions; 
    }

    public LocalDateTime getPrescriptionDate() { 
        return prescriptionDate; 
    }
    
    public void setPrescriptionDate(LocalDateTime prescriptionDate) { 
        this.prescriptionDate = prescriptionDate; 
    }
}