package com.example.dzanicprojekat.Repositories;


import com.example.dzanicprojekat.Entities.Frizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrizerRepo extends JpaRepository<Frizer,Long> {
    Frizer readById(long id);
    void deleteById(long id);
    List<Frizer> findAllByAvailable(boolean isActive);

}
