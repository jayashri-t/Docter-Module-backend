package com.hospital.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.model.DoctorProfile;

import java.util.Optional;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {

    Optional<DoctorProfile> findByRegistrationNumber(String registrationNumber);

    Optional<DoctorProfile> findByEmail(String email);
    
    Optional<DoctorProfile> findByUserId(Long userId);
}