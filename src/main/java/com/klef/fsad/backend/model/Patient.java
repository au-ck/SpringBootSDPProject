package com.klef.fsad.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_table")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;

    @Column(name = "patient_name", length = 50, nullable = false)
    private String name;

    @Column(name = "patient_gender", length = 10, nullable = false)
    private String gender;

    @Column(name = "patient_dob", length = 20, nullable = false)
    private String dob;

    @Column(name = "patient_email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "patient_username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "patient_password", length = 50, nullable = false)
    private String password;

    @Column(name = "patient_mobileno", length = 20, nullable = false, unique = true)
    private String mobileno;

    @Column(name = "patient_location", length = 50, nullable = false)
    private String location;

    @Column(name = "patient_status", length = 20)
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}