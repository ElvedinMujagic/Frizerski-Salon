package com.example.dzanicprojekat.Repositories;


import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.Termin;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Utility.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepo extends JpaRepository<Termin,Long> {
    List<Termin> findAllByFrizerAndState(Frizer frizer, State state);
    List<Termin> findAllByUser(User user);
}
