package com.example.dzanicprojekat.Services;


import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Repositories.FrizerRepo;
import com.example.dzanicprojekat.Repositories.UserRepo;
import com.example.dzanicprojekat.Utility.DTOs.FrizerDTO;
import com.example.dzanicprojekat.Utility.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FrizerService {
    private final FrizerRepo frizerRepo;
    private final UserRepo userRepo;

    public Frizer getById(Long id) {
        return frizerRepo.readById(id);
    }

    public void addFrizer(FrizerDTO frizerDTO) {
        User user = userRepo.findById(frizerDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Frizer frizer = new Frizer();
        frizer.setUser(user);
        frizer.setSpecijalizacija(frizerDTO.getSpecijalizacija());
        frizer.setGodineIskustva(frizerDTO.getGodineIskustva());
        frizer.setAvailable(frizerDTO.isAvailable());
        user.setRole(Role.FRIZER);
        frizerRepo.save(frizer);
    }
    public boolean checkById(Long id) {
        return frizerRepo.existsById(id);
    }
    public FrizerService(FrizerRepo frizerRepo, UserRepo userRepo) {
        this.frizerRepo = frizerRepo;
        this.userRepo = userRepo;
    }
}
