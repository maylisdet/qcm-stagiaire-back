package com.ai13qcm.dto;

import com.ai13qcm.entities.Role;

public class UserDTO {
    private String firstname, lastname, email, company, phone, role;
    private boolean isActive;
    public UserDTO(String firstname, String lastname, String email, String company, String phone, boolean isActive) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.company = company;
        this.phone = phone;
        this.isActive = isActive;
        this.role = Role.ROLE_TRAINEE;
    }

    public UserDTO(String firstname, String lastname, String email, String company, String phone, boolean isActive, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.company = company;
        this.phone = phone;
        this.isActive = isActive;
        this.role = role;

    }

    public UserDTO() {
        this.role = Role.ROLE_TRAINEE;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
