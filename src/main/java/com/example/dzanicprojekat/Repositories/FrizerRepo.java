package com.example.dzanicprojekat.Repositories;


import com.example.dzanicprojekat.Entities.Frizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrizerRepo extends JpaRepository<Frizer,Long> {
    Frizer readByUsername(String username);

}
