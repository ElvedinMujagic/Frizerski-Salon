package com.example.dzanicprojekat.Entities;



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
    @ColumnDefault("0.0")
    private double popust;
    @ColumnDefault("0")
    private boolean confirmed;
    @ColumnDefault("0")
    private boolean canceled;
    @ColumnDefault("'N/A'")
    private String cancellation_reason;

    @ManyToMany()
    @JoinTable(
            name = "termin_usluga",
            joinColumns = @JoinColumn(name = "termin_id"),
            inverseJoinColumns = @JoinColumn(name = "usluga_id")
    )
    private List<Usluga> usluge;

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


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.pocetak_termina = LocalTime.MIDNIGHT;
        this.datum_termina = LocalDate.now();
    }
}
