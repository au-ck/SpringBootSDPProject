package com.klef.fsad.backend.service;

import java.util.List;
import com.klef.fsad.backend.model.*;

public interface AdminService {
    Admin checkadminLogin(String username, String password);
    String addDoctor(Doctor doctor);
    List<Doctor> displayDoctors();
    List<Patient> displayPatients();
    String deleteDoctor(int id);
    String deletePatient(int pid);
}
