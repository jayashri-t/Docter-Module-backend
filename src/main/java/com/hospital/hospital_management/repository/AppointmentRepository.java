package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIdAndAppointmentDateAfter(Long doctorId, LocalDate date);

    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    List<Appointment> findByPatientIdAndStatus(Long patientId, String status);

    List<Appointment> findByPatientIdAndAppointmentDate(Long patientId, LocalDate date);

    List<Appointment> findByPatientId(Long patientId);

    long countByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    long countByDoctorIdAndStatus(Long doctorId, String status);
}