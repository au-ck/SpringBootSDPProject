package com.klef.fsad.backend.service;

import com.klef.fsad.backend.model.Doctor;
import com.klef.fsad.backend.model.Appointment;
import java.util.List;

public interface DoctorService {
    Doctor checkDoctorLogin(String username, String password);
    Doctor getDoctorProfile(int id);
    List<Appointment> getDoctorAppointments(String doctorName);
}
