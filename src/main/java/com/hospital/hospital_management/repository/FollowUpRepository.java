package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    List<FollowUp> findByDoctorIdAndFollowUpDate(Long doctorId, LocalDate followUpDate);

    // Missing methods required by FollowUpServiceImpl
    List<FollowUp> findByPatientIdOrderByFollowUpDateDesc(Long patientId);

    List<FollowUp> findByPrescriptionId(Long prescriptionId);

    // Case-Insensitive pending follow-ups check
    @Query("SELECT COUNT(f) FROM FollowUp f WHERE f.doctorId = :doctorId AND LOWER(f.status) = LOWER(:status)")
    long countByDoctorIdAndStatusIgnoreCase(@Param("doctorId") Long doctorId, @Param("status") String status);

    // Doctor total Follow-ups
    long countByDoctorId(Long doctorId);

    // Emergency Cases count
    @Query("SELECT COUNT(f) FROM FollowUp f WHERE f.doctorId = :doctorId AND (LOWER(f.reason) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.notes) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    long countByDoctorIdAndReasonOrNotesContaining(@Param("doctorId") Long doctorId, @Param("keyword") String keyword);
}