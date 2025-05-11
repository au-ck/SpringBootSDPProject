package com.klef.fsad.backend.repository;

import com.klef.fsad.backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByUsernameAndPassword(String username, String password);
}
