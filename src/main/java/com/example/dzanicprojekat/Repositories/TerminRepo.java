package com.example.dzanicprojekat.Repositories;


import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.Termin;
import com.example.dzanicprojekat.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepo extends JpaRepository<Termin,Long> {
    List<Termin> findAllByFrizerAndConfirmed(Frizer frizer, boolean confirmed);
    List<Termin> findAllByUser(User user);
}
