package com.example.dzanicprojekat.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Frizer extends User {

    private String specijalizacija;

    private int godineIskustva;
    private boolean available;

}
