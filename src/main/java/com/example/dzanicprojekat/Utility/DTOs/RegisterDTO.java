package com.example.dzanicprojekat.Utility.DTOs;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter @Setter @ToString
public class RegisterDTO {
    @Email(message = "Nije ispravan format email-a")
    @NotBlank(message = "Email je potreban!")
    private String email;
    @Size(min=6, message = "Password mora da sadrzi barem 6 znakova")
    private String password;
    @Size(min=3, message = "Ime mora da sadrzi barem 3 znaka")
    private String ime;
    @Size(min=3, message = "Prezime mora da sadrzi barem 3 znaka")
    private String prezime;
    @Size(min=3, message = "Korisnicko ime mora da sadrzi barem 3 znaka")
    private String username;
    private String spol;
    private String confPass;

    public boolean validPassword() {
        return confPass.equals(password);
    }



}
