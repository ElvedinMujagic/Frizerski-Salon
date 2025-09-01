package com.example.dzanicprojekat.Utility.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {

    private String ime;
    private String prezime;
    private String username;
    private String spol;
    private String brojTelefona;
    private String specijalizacija;
    private boolean available;

}
