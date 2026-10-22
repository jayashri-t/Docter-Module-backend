package com.hospital.hospital_management.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_name")
    private String patientName;

    private String contact;
    private Integer age;
    private String gender;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;

    @Column(name = "appointment_date_time")
    private LocalDateTime appointmentDateTime;

    private String status;   // e.g., "SCHEDULED", "COMPLETED", "CANCELLED", "In-Progress"

    @Column(name = "care_type")
    private String careType; // e.g., "IN_PERSON", "EMERGENCY", "TELECONSULT"

    private String reason;

    @Column(name = "referred_by")
    private String referredBy;

    @Column(name = "token_number")
    private Integer tokenNumber;

    public Appointment() {}

    public Appointment(Long id, Long doctorId, Long patientId, String patientName, 
                       String contact, Integer age, String gender, LocalDate appointmentDate, 
                       String appointmentTime, LocalDateTime appointmentDateTime, 
                       String status, String careType, String reason, String referredBy, Integer tokenNumber) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
        this.careType = careType;
        this.reason = reason;
        this.referredBy = referredBy;
        this.tokenNumber = tokenNumber;
    }

    @Transient
    public String getFormattedPatientId() {
        if (this.patientId != null) {
            return String.format("PAT-%04d", this.patientId);
        }
        return null;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) { this.appointmentDateTime = appointmentDateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCareType() { return careType; }
    public void setCareType(String careType) { this.careType = careType; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getReferredBy() { return referredBy; }
    public void setReferredBy(String referredBy) { this.referredBy = referredBy; }

    public Integer getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(Integer tokenNumber) { this.tokenNumber = tokenNumber; }
    
    @PrePersist
    public void prePersist() {
        if (this.appointmentDate == null) {
            this.appointmentDate = LocalDate.now();
        }
    }
}