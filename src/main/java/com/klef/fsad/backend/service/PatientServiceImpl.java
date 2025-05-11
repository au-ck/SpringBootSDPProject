package com.klef.fsad.backend.service;

import com.klef.fsad.backend.model.Patient;
import com.klef.fsad.backend.model.Appointment;
import com.klef.fsad.backend.repository.PatientRepository;
import com.klef.fsad.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired private PatientRepository patientRepo;
    @Autowired private AppointmentRepository appointmentRepo;

    @Override
    public String registerPatient(Patient patient) {
        patientRepo.save(patient);
        return "Patient registered successfully";
    }

    @Override
    public Patient checkPatientLogin(String username, String password) {
        return patientRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public Patient getPatientProfile(int id) {
        return patientRepo.findById(id).orElse(null);
    }

    @Override
    public String requestAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
        return "Appointment request sent!";
    }

    @Override
    public List<Appointment> getPatientAppointments(int patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }
}
