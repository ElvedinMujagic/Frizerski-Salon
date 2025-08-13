package com.example.dzanicprojekat.Utility.DTOs;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FrizerDTO {
    @PositiveOrZero(message = "ID ne može da bude negativan broj")
    private Long id;
    private String specijalizacija;
    @PositiveOrZero(message = "Iskustvno ne može da bude negativan broj")
    private int godineIskustva;
    private boolean available;
}
