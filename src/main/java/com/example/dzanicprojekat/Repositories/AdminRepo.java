package com.example.dzanicprojekat.Repositories;

import com.example.dzanicprojekat.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Admin readByUsername(String username);

}
