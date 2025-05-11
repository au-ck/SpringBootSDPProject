package com.klef.fsad.backend.service;

import com.klef.fsad.backend.model.Doctor;
import com.klef.fsad.backend.model.Appointment;
import com.klef.fsad.backend.repository.DoctorRepository;
import com.klef.fsad.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired private DoctorRepository doctorRepo;
    @Autowired private AppointmentRepository appointmentRepo;

    @Override
    public Doctor checkDoctorLogin(String username, String password) {
        return doctorRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public Doctor getDoctorProfile(int id) {
        return doctorRepo.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getDoctorAppointments(String doctorName) {
        return appointmentRepo.findByDoctorName(doctorName);
    }
}
