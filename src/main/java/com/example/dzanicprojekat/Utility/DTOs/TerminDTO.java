package com.example.dzanicprojekat.Utility.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TerminDTO {
    private Long terminId;
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
