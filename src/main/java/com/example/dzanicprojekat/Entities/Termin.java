package com.example.dzanicprojekat.Entities;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private double price_full;
    private double discount;
    private double price_final;
    private String client_name;
    private String client_contact;
    private boolean confirmed;
    private boolean canceled;
    private String cancellation_reason;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frizer_id")
    private Frizer frizer;

    // Ovo će stvoriti još jednu tabelu pod naziv "termin_usluga"
    // U njoj se nalaze ID termina i ID usluge
    // Na taj način spremamo više usluga u jedan termin aka;
    // termin_id:1 <----> usluga_id:1
    // termin_id:1 <----> usluga_id:2
    // termin_id:2 <----> usluga_id:3
    @ManyToMany
    @JoinTable(
            name = "termin_usluga",
            joinColumns = @JoinColumn(name = "termin_id"),
            inverseJoinColumns = @JoinColumn(name = "usluga_id")
    )
    private List<Usluga> usluge;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
