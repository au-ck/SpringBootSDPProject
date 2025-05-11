// DoctorController.java (Backend)

package com.klef.fsad.backend.controller;

import com.klef.fsad.backend.model.Appointment;
import com.klef.fsad.backend.model.Doctor;
import com.klef.fsad.backend.repository.AppointmentRepository;
import com.klef.fsad.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/checkdoctorlogin")
    public ResponseEntity<?> checkLogin(@RequestBody Doctor input) {
        Doctor doctor = doctorRepository.findByUsernameAndPassword(input.getUsername(), input.getPassword());
        if (doctor != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", doctor.getId());
            response.put("name", doctor.getName());
            response.put("email", doctor.getEmail());
            response.put("specialization", doctor.getSpecialization());
            response.put("experience", doctor.getExperience());
            response.put("mobile", doctor.getMobile());
            response.put("location", doctor.getLocation());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable int id) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", doctor.getId());
                    response.put("name", doctor.getName());
                    response.put("email", doctor.getEmail());
                    response.put("specialization", doctor.getSpecialization());
                    response.put("experience", doctor.getExperience());
                    response.put("mobile", doctor.getMobile());
                    response.put("location", doctor.getLocation());
                    return ResponseEntity.ok(response);
                })
                .orElseThrow();
    }

    // Fetch appointments for the doctor
    @GetMapping("/appointments/{doctorName}")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable String doctorName) {
        List<Appointment> appointments = appointmentRepository.findByDoctorName(doctorName);
        return ResponseEntity.ok(appointments);
    }

    // Approve or reject appointment
    @PostMapping("/updateAppointmentStatus")
    public ResponseEntity<String> updateAppointmentStatus(@RequestBody Appointment request) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(request.getId());
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(request.getStatus());
            appointmentRepository.save(appointment);
            return ResponseEntity.ok("Appointment status updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Appointment not found.");
        }
    }
}