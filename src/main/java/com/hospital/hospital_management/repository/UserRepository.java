package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Email द्वारे युजर शोधण्यासाठी
    Optional<User> findByEmail(String email);

    // Phone Number द्वारे युजर शोधण्यासाठी
    Optional<User> findByPhoneNumber(String phoneNumber);
}