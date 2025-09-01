package com.example.dzanicprojekat.Entities;



import com.example.dzanicprojekat.Utility.State;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalDate datum_termina;
    private LocalTime pocetak_termina;
    private double cijena;
    @Enumerated(EnumType.STRING)
    private State state;
    @ColumnDefault("'N/A'")
    private String cancellation_reason;
    @ManyToMany()
    @JoinTable(
            name = "termin_usluga",
            joinColumns = @JoinColumn(name = "termin_id"),
            inverseJoinColumns = @JoinColumn(name = "usluga_id"))
    private List<Usluga> usluge;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "frizer_id")
    private Frizer frizer;
    @PrePersist
    protected void onCreate() {
        this.state = State.Unconfirmed;
        this.createdAt = LocalDateTime.now();
        this.pocetak_termina = LocalTime.MIDNIGHT;
        this.datum_termina = LocalDate.now();
    }
}
