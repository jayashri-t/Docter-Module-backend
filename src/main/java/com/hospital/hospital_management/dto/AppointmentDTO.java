package com.hospital.hospital_management.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentDTO {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private String formattedPatientId; // e.g. "PAT-0004"
    @JsonAlias({"name", "fullName"})
    private String patientName;
    @JsonAlias({"phoneNumber", "phone", "mobile", "contactNumber", "mobileNumber"})
    private String contact;
    private Integer age;
    private String gender;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private LocalDateTime appointmentDateTime;
    private String status;
    private String careType;

    // No-args constructor
    public AppointmentDTO() {}

    // All-args constructor
    public AppointmentDTO(Long id, Long doctorId, Long patientId, String patientName, 
                          String contact, Integer age, String gender, LocalDate appointmentDate, 
                          String appointmentTime, LocalDateTime appointmentDateTime, 
                          String status, String careType) {
        this.id = id;
        this.doctorId = doctorId;
        setPatientId(patientId); // Ensures formattedPatientId is calculated automatically
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

    // Getters and Setters

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
        if (patientId != null) {
            this.formattedPatientId = String.format("PAT-%04d", patientId);
        } else {
            this.formattedPatientId = null;
        }
    }

    public String getFormattedPatientId() { 
        return formattedPatientId; 
    }
    
    public void setFormattedPatientId(String formattedPatientId) { 
        this.formattedPatientId = formattedPatientId; 
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