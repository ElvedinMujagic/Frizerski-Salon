package com.example.dzanicprojekat.Entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    @Column(length = 1000)
    private String notes;

    private int loyaltyPoints;
}
