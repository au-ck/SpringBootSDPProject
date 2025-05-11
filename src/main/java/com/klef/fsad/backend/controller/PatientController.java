package com.klef.fsad.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.backend.model.Appointment;
import com.klef.fsad.backend.model.Patient;
import com.klef.fsad.backend.repository.AppointmentRepository;
import com.klef.fsad.backend.repository.PatientRepository;
import com.klef.fsad.backend.service.PatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {

    @Autowired private PatientRepository pr;
    @Autowired private AppointmentRepository ar;
    @Autowired private PatientService ps;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody Patient patient) {
        patient.setStatus("Pending"); // default status
        pr.save(patient);
        return ResponseEntity.ok("Patient registered successfully");
    }

    @PostMapping("/checkpatientlogin")
    public ResponseEntity<?> checkPatientLogin(@RequestBody Patient input) {
        Patient patient = ps.checkPatientLogin(input.getUsername(), input.getPassword());
        if (patient != null) {
            return ResponseEntity.ok(patient); // includes status
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/profile/{pid}")
    public ResponseEntity<?> getProfile(@PathVariable int pid) {
        return pr.findById(pid).map(ResponseEntity::ok).orElseThrow();
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable int id) {
        List<Appointment> appointments = ar.findByPatientId(id);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/appointments")
    public ResponseEntity<?> requestAppointment(@RequestBody Appointment appointment) {
        ar.save(appointment);
        return ResponseEntity.ok("Appointment request sent!");
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approvePatient(@PathVariable int id) {
        return pr.findById(id).map(patient -> {
            patient.setStatus("Approved");
            pr.save(patient);
            return ResponseEntity.ok("Patient approved");
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectPatient(@PathVariable int id) {
        return pr.findById(id).map(patient -> {
            patient.setStatus("Rejected");
            pr.save(patient);
            return ResponseEntity.ok("Patient rejected");
        }).orElse(ResponseEntity.notFound().build());
    }
}