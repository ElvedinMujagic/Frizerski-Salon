package com.example.dzanicprojekat.Repositories;

import com.example.dzanicprojekat.Entities.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UslugeRepo extends JpaRepository<Usluga,Long> {
    boolean existsByNaziv(String email);
}
