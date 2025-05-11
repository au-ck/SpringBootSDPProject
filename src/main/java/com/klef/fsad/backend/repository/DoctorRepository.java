package com.klef.fsad.backend.repository;

import com.klef.fsad.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByUsernameAndPassword(String username, String password);
    Doctor findByUsername(String username);
}
