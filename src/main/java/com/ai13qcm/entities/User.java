package com.ai13qcm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Table(name = "user")
@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false)

    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    private String firstname;
    private String lastname;
    private String email;
    @JsonIgnore
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private String company;
    private String phone;
    private Timestamp createdAt;
    private boolean isActive;

    public User(Integer id, String firstname, String lastname, String email, String password, Role role, String company, String phone, Timestamp createdAt, boolean isActive) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.company = company;
        this.phone = phone;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public User(String firstname, String lastname, String email, String phone, String company , String password, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
        this.phone = phone;
        this.company = company;
        this.createdAt = Timestamp.from(Instant.now());

    }

    public User() {
        this.isActive = true;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp creationDate) {
        this.createdAt = creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}