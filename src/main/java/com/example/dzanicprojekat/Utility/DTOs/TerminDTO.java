package com.example.dzanicprojekat.Utility.DTOs;

import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Entities.Usluga;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class TerminDTO {
    private Long userId;
    @NotNull(message = "Morate odabrati frizera!")
    private Long frizerId;
    @NotNull(message = "Morate odabrati uslugu!")
    private Long uslugeId;
    @NotNull(message = "Morate odabrati datum!")
    private LocalDate datum;
    private double cijena;
    private boolean confirmed;
    private boolean canceled;
    private String cancellation_reason;

}
