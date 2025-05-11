package com.klef.fsad.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.backend.model.Admin;
import com.klef.fsad.backend.model.Doctor;
import com.klef.fsad.backend.model.Patient;
import com.klef.fsad.backend.repository.DoctorRepository;
import com.klef.fsad.backend.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DoctorRepository d;

    @PostMapping("/checkadminlogin")
    public ResponseEntity<?> checkAdminLogin(@RequestBody Admin admin) {
        try {
            Admin a = adminService.checkadminLogin(admin.getUsername(), admin.getPassword());
            if (a != null) {
                return ResponseEntity.ok(a);
            } else {
                return ResponseEntity.status(401).body("Invalid Username or Password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/adddoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        try {
            d.save(doctor);
            return ResponseEntity.ok("Doctor added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add doctor: " + e.getMessage());
        }
    }

    @GetMapping("/alldoctors")
    public ResponseEntity<List<Doctor>> viewAllDoctors() {
        List<Doctor> doctors = adminService.displayDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/allpatients")
    public ResponseEntity<List<Patient>> viewallpatients() {
        List<Patient> patients = adminService.displayPatients();
        return ResponseEntity.ok(patients);
    }

    @DeleteMapping("/deletedoctor")
    public ResponseEntity<String> deleteDoctor(@RequestParam int id) {
        try {
            String result = adminService.deleteDoctor(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to Delete Doctor ... !!");
        }
    }

    @DeleteMapping("/deletepatient")
    public ResponseEntity<String> deletePatient(@RequestParam int pid) {
        try {
            String result = adminService.deletePatient(pid);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to Delete Patient ... !!");
        }
    }
}
