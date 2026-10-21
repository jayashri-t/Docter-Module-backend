package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Dashboard Statistics Counters
    long countByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    long countByDoctorIdAndStatus(Long doctorId, String status);

    long countByDoctorIdAndCareType(Long doctorId, String careType);

    // Doctor Workspace - Filter appointments by doctor and status
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, String status);

    // Doctor Workspace - Filter appointments by doctor and care type
    List<Appointment> findByDoctorIdAndCareType(Long doctorId, String careType);

    // Fetch Today's Schedule ordered by time
    List<Appointment> findByDoctorIdAndAppointmentDateOrderByAppointmentTimeAsc(Long doctorId, LocalDate date);

    // Fetch Upcoming Appointments from current time onwards
    List<Appointment> findByDoctorIdAndAppointmentDateTimeAfterOrderByAppointmentDateTimeAsc(Long doctorId, LocalDateTime dateTime);

    // Patient Workspace - Filter appointments by patient & status tabs
    List<Appointment> findByPatientIdAndStatus(Long patientId, String status);

    // Patient Workspace - Filter appointments by patient & appointment date
    List<Appointment> findByPatientIdAndAppointmentDate(Long patientId, LocalDate date);

    // Fetch all appointments for a specific patient
    List<Appointment> findByPatientIdOrderByAppointmentDateDesc(Long patientId);
}