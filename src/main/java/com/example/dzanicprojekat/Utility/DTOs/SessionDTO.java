package com.example.dzanicprojekat.Utility.DTOs;

import com.example.dzanicprojekat.Entities.Admin;
import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Utility.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {

    // User Attributes
    private String ime;
    private String prezime;
    private String username;
    private String email;
    private String brojTelefona;
    private Role role;
    private LocalDateTime createdAt;
    private String spol;

    // Client Attributes
    private String notes;
    private int loyaltyPoints;
    // Frizer attributes
    private int godineIskustva;
    private String specijalizacija;
    private boolean available;
    // Admin attributes
    private boolean superAdmin;


    public SessionDTO(User user) {
        this.ime = user.getIme();
        this.prezime = user.getPrezime();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.brojTelefona = user.getBrojTelefona();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.spol = user.getSpol();
    }

    public SessionDTO(User user,Admin admin) {
        this.ime = user.getIme();
        this.prezime = user.getPrezime();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.brojTelefona = user.getBrojTelefona();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.spol = user.getSpol();
        this.superAdmin = admin.isSuperAdmin();
    }

    public SessionDTO(User user,Frizer frizer) {
        this.ime = user.getIme();
        this.prezime = user.getPrezime();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.brojTelefona = user.getBrojTelefona();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.spol = user.getSpol();
        this.specijalizacija = frizer.getSpecijalizacija();
        this.godineIskustva = frizer.getGodineIskustva();
        this.available = frizer.isAvailable();
    }

    public String getImePrezime() {
        return this.ime + " " + this.prezime;
    }
}
