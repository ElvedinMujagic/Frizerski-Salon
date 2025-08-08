package com.example.dzanicprojekat.Repositories;


import com.example.dzanicprojekat.Entities.Frizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrizerRepo extends JpaRepository<Frizer,Long> {
    Frizer readById(long id);

}
