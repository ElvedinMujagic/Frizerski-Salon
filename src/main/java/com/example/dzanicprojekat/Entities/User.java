package com.example.dzanicprojekat.Entities;

import jakarta.persistence.*;
import com.example.dzanicprojekat.Utility.Role;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ime;
    private String prezime;
    private String spol;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String hash;

    private String brojTelefona;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        role=Role.CLIENT;
    }

    public User(User user) {}
}
