package com.klef.fsad.backend.service;

import com.klef.fsad.backend.model.Patient;
import com.klef.fsad.backend.model.Appointment;
import java.util.List;

public interface PatientService {
    String registerPatient(Patient patient);
    Patient checkPatientLogin(String username, String password);
    Patient getPatientProfile(int id);
    String requestAppointment(Appointment appointment);
    List<Appointment> getPatientAppointments(int patientId);
}
