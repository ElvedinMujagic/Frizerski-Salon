package com.example.dzanicprojekat.Utility.DTOs;

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
