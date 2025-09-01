package com.example.dzanicprojekat.Utility.DTOs;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UslugaDTO {
    @NotBlank(message = "Naziv je potreban!!")
    private String naziv;
    @NotNull(message = "Cijena je potrebna!")
    @Min(value = 1, message = "Cijena mora biti najmanje 1 KM")
    private double cijena;
    @NotNull(message = "Trajanje je potrebno!")
    @Min(value = 1, message = "Trajanje mora biti najkraće 1 minuta")
    private int trajanje;
    private boolean active;
}
