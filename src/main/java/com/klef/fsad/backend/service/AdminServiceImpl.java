package com.klef.fsad.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.backend.model.*;
import com.klef.fsad.backend.repository.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired private AdminRepository a;
    @Autowired private DoctorRepository d;
    @Autowired private PatientRepository p;

    @Override
    public Admin checkadminLogin(String username, String password) {
        return a.findByUsernameAndPassword(username, password);
    }

    @Override
    public String addDoctor(Doctor doctor) {
        d.save(doctor);
        return "Doctor added successfully!";
    }


    @Override
    public List<Doctor> displayDoctors() {
        return d.findAll();
    }

    @Override
    public List<Patient> displayPatients() {
        return p.findAll();
    }

    @Override
    public String deleteDoctor(int id) {
        d.deleteById(id);
        return "Doctor deleted successfully";
    }

    @Override
    public String deletePatient(int pid) {
        p.deleteById(pid);
        return "Patient deleted successfully";
    }
}
