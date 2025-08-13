package com.example.dzanicprojekat.Repositories;

import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Utility.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User readByUsername(String username);
    User readById(Long id);
    List<User> findByRoleNotIn(List<Role> roles);
}
