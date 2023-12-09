package com.example.uber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "admin_table")
@Getter
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "encrypted_password")
    @With
    private String encryptedPassword;

    @Column(name = "first_name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public Admin() {
    }

    public Admin(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
}
