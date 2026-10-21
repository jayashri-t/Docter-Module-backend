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

    private Long doctorId;
    private Long patientId; // Standard Long ID - (No Patient Class Needed)

    private String patientName;
    private String contact;
    private Integer age;
    private String gender;

    private LocalDate appointmentDate;
    private String appointmentTime;
    private LocalDateTime appointmentDateTime;
    private String status;   // e.g., "SCHEDULED", "COMPLETED", "CANCELLED", "In-Progress"
    private String careType; // e.g., "IN_PERSON", "EMERGENCY", "TELECONSULT"

    // Default No-Args Constructor
    public Appointment() {}

    // All-Args Constructor
    public Appointment(Long id, Long doctorId, Long patientId, String patientName, 
                       String contact, Integer age, String gender, LocalDate appointmentDate, 
                       String appointmentTime, LocalDateTime appointmentDateTime, 
                       String status, String careType) {
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
    }

    // --- Helper Method ---
    @Transient
    public String getFormattedPatientId() {
        if (this.patientId != null) {
            return String.format("PAT-%04d", this.patientId);
        }
        return null;
    }

    // --- Getters & Setters ---

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

    public String getPatientName() { 
        return patientName; 
    }
    
    public void setPatientName(String patientName) { 
        this.patientName = patientName; 
    }

    public String getContact() { 
        return contact; 
    }
    
    public void setContact(String contact) { 
        this.contact = contact; 
    }

    public Integer getAge() { 
        return age; 
    }
    
    public void setAge(Integer age) { 
        this.age = age; 
    }

    public String getGender() { 
        return gender; 
    }
    
    public void setGender(String gender) { 
        this.gender = gender; 
    }

    public LocalDate getAppointmentDate() { 
        return appointmentDate; 
    }
    
    public void setAppointmentDate(LocalDate appointmentDate) { 
        this.appointmentDate = appointmentDate; 
    }

    public String getAppointmentTime() { 
        return appointmentTime; 
    }
    
    public void setAppointmentTime(String appointmentTime) { 
        this.appointmentTime = appointmentTime; 
    }

    public LocalDateTime getAppointmentDateTime() { 
        return appointmentDateTime; 
    }
    
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) { 
        this.appointmentDateTime = appointmentDateTime; 
    }

    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getCareType() { 
        return careType; 
    }
    
    public void setCareType(String careType) { 
        this.careType = careType; 
    }
}