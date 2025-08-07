package com.example.dzanicprojekat.Repositories;

import com.example.dzanicprojekat.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Client readByUsername(String username);
}
