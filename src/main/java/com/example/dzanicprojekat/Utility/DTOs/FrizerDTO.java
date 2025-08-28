package com.example.dzanicprojekat.Utility.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FrizerDTO {
    private Long id;
    private String specijalizacija;
    private int godineIskustva;
    private boolean available;
}
