package com.example.dzanicprojekat.Utility.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private String fullName;
    private String phone;
    private String email;
    private String service;
    private LocalDate date;
    private LocalTime time;
    private String notes;
}
