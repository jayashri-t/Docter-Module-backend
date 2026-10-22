package com.hospital.hospital_management.model;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "follow_ups")
@Data
@NoArgsConstructor  // <--- HE ADD KELAY (No-argument constructor sathi)
@AllArgsConstructor
public class FollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public FollowUp() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Long patientId;
    private Long doctorId;
    
    // Ensure this field exists if you are using method-name derivation like findByPrescriptionId
    private Long prescriptionId; 
    
    private LocalDate followUpDate;
    private String reason;
    private String notes;
    private String status;
	public FollowUp(Long id, Long patientId, Long doctorId, Long prescriptionId, LocalDate followUpDate, String reason,
			String notes, String status) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.prescriptionId = prescriptionId;
		this.followUpDate = followUpDate;
		this.reason = reason;
		this.notes = notes;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public LocalDate getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}