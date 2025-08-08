package com.example.dzanicprojekat.Repositories;

import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Utility.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User readByUsername(String username);

}
