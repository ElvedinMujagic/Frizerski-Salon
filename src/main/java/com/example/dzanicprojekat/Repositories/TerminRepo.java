package com.example.dzanicprojekat.Repositories;


import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepo extends JpaRepository<Termin,Long> {
    List<Termin> findAllByFrizer(Frizer byId);
}
